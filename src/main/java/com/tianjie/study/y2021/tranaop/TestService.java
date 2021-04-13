package com.tianjie.study.y2021.tranaop;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TestService {

    private static ExecutorService executors = Executors.newFixedThreadPool(10);

    @Transactional
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            final Integer a = i;
            String lockID = UUID.randomUUID().toString();
            TestSync sync = new TestSync(lockID);
            if(TransactionSynchronizationManager.isActualTransactionActive() && !TransactionSynchronizationManager.getSynchronizations().contains(sync)){
                TransactionSynchronizationManager.registerSynchronization(sync);
            }
            final Tlock loc = TranUtil.get(lockID);
            executors.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Boolean aBoolean = loc.waitTran();
                    if(!aBoolean){
                        System.out.println(a+"等待超时提交");
                    }else{
                        System.out.println(a+"等待主线程事务提交");
                    }

                }
            });
        }
        Thread.sleep(20000);

    }

}
