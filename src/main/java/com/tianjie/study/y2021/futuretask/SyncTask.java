package com.tianjie.study.y2021.futuretask;

import java.util.concurrent.CountDownLatch;

public class SyncTask<T> {

    private T result;

    private CountDownLatch countDownLatch;

    public SyncTask() {
        countDownLatch = new CountDownLatch(1);
    }

    public T get() throws InterruptedException {
        countDownLatch.await();
        return result;
    }

    public void complete(T t){
        this.result = t;
        countDownLatch.countDown();
    }
}
