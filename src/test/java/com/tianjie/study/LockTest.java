package com.tianjie.study;

import com.tianjie.study.oracl.DeptMapper;
import com.tianjie.study.oracl.Lock;
import com.tianjie.study.oracl.LockMapper;
import com.tianjie.study.util.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.concurrent.*;

@SpringBootTest
public class LockTest {

    @Autowired
    private LockMapper lockMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testLock() throws Exception {
//
        //模拟业务住建
        String [] keys = {"1","2","3"};
        ExecutorService service = new ThreadPoolExecutor(2,20,300l, TimeUnit.SECONDS,new ArrayBlockingQueue<>(20));
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //模拟分布式服务同步操作
        FutureTask<Boolean> futureTask1 = new FutureTask<>(new LockTask("10", countDownLatch,lockMapper));

        FutureTask<Boolean> futureTask2= new FutureTask<>(new LockTask("10", countDownLatch,lockMapper));
        service.execute(futureTask1);
        service.execute(futureTask2);



        try {
            System.out.println("task1获取到"+futureTask1.get(3,TimeUnit.SECONDS));
            System.out.println("task2获取到"+futureTask2.get(3,TimeUnit.SECONDS));

        } catch (Exception e) {
            e.printStackTrace();

        }
//        Thread.sleep(10000L);

    }

    class LockTask  implements Callable<Boolean> {

        private String key;

        private CountDownLatch countDownLatch;

        private LockMapper lockMapper;

        public LockTask(String key,CountDownLatch countDownLatch,LockMapper lockMapper) {
            this.key = key;
            this.countDownLatch = countDownLatch;
            this.lockMapper = lockMapper;
        }

        @Override
        public Boolean call() {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
               Lock lock = new Lock();
               lock.setLockId(key);
               lock.insert();
//               System.out.println("获取成功");
           }catch (Exception e){
//               e.printStackTrace();
//               System.out.println("获取失败");
               return false;
           }
            return true;
        }
    }
}
