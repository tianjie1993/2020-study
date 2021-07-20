package com.tianjie.study.y2021.tranaop;

import com.tianjie.study.y2020.mysql.TUser;
import com.tianjie.study.y2020.mysql.UserMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TestService {

    @Autowired
    private UserMapper userMapper;

    private static ExecutorService executors = Executors.newFixedThreadPool(10);

//    @Transactional
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            final Integer a = i;
            String lockID = UUID.randomUUID().toString();
            if(TransactionSynchronizationManager.isActualTransactionActive() ){
                TestSync sync = new TestSync(lockID);
                TransactionSynchronizationManager.registerSynchronization(sync);
            }
            final Tlock loc = TranUtil.get(lockID);
            executors.submit(() -> {
                Boolean notTimeout = loc.waitTran();
                if(!notTimeout){
                    System.out.println(a+"等待超时提交");
                }else{
                    System.out.println(a+"等待主线程事务提交");
                }

            });
        }
        Thread.sleep(20000);

    }

    @Transactional
    public void testThreadTransction(){
        TUser user = new TUser(99,"tianjie",23);
        userMapper.insert(user);
    }

}
