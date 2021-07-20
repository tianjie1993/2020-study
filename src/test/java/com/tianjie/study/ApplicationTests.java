package com.tianjie.study;

import com.tianjie.study.y2020.mysql.TUser;
import com.tianjie.study.y2020.mysql.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveTask;



//@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserService userService;


    @Test
    void contextLoads() {
    }


    @Test
    void testOp64() throws InterruptedException {
//        Long p1,p2,p3,p4,p5,p6,p7;
        Long counts = 1_0000_0000L;
//        Long p8,p9,p10,p11,p12,p13,p14;

        Long[]x = new Long[2];
        CountDownLatch downLatch = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
            for (long i = 0; i < counts; i++) {
                x[1] = i;
            }
            downLatch.countDown();
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < counts; i++) {
                x[0] = i;
            }
            downLatch.countDown();
        });
        long d1 = System.currentTimeMillis();
        t1.start();
        t2.start();
        downLatch.await();
        System.out.println(System.currentTimeMillis()-d1);

    }


    /**
     *
     * @author tian.jie
     * @email tian.jie@rongzer.com
     * @param
     * @date 2020-08-19 15:31
     * @return
     */
//    @Test
    void userInsert(){


        InsertTask task1 = new InsertTask(0);
        InsertTask task2 = new InsertTask(1000000);
        InsertTask task3 = new InsertTask(2000000);
        InsertTask task4 = new InsertTask(3000000);
        InsertTask task5 = new InsertTask(4000000);

        task1.fork();
        task2.fork();
        task3.fork();
        task4.fork();
        task5.fork();

        task1.join();
        task2.join();
        task3.join();
        task4.join();
        task5.join();



    }

    class InsertTask extends RecursiveTask {

        private Integer start;

        public InsertTask(Integer start) {
            this.start = start;
        }

        @Override
        protected Object compute() {
            int i = 0;
            List<TUser> userList = new ArrayList<>();
            while(i<1000000){
                TUser user = new TUser(start,"哈哈"+start,(int)(Math.random()*100));
                userList.add(user);
                if(userList.size() ==1000){
                    userService.saveBatch(userList);
                    userList.clear();
                }
                start++;
                i++;
            }
            return start;
        }

    }

}
