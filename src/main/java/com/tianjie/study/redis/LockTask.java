package com.tianjie.study.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
public class LockTask implements Runnable{

    private StringRedisTemplate redisTemplate;

    private CountDownLatch countDownLatch;

    public LockTask(StringRedisTemplate redisTemplate, CountDownLatch countDownLatch) {
        this.redisTemplate = redisTemplate;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Boolean gelock = redisTemplate.opsForValue().setIfAbsent("lock","",5, TimeUnit.MINUTES);
        if(gelock){
            System.out.println("执行定时任务");
            try {
                //模拟业务处理
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisTemplate.opsForValue().getOperations().delete("lock");
        }else{
            System.out.println("其他服务已执行任务");
        }


    }
}
