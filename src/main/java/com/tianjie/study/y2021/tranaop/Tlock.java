package com.tianjie.study.y2021.tranaop;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Tlock {

    private String id;

    private CountDownLatch countDownLatch;


    public Tlock(String id) {
        this.id = id;
        this.countDownLatch = new CountDownLatch(1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void complete(){
        countDownLatch.countDown();
    }

    public Boolean waitTran(){
        try {
            return countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
