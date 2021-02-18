package com.tianjie.study.y2020.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedissonTest {

    public static void main(String[] args) {
        RedissonClient redissonClient = Redisson.create();
        RLock lock = redissonClient.getLock("ad");
        lock.lock();
        lock.unlock();
    }
}

