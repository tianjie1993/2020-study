package com.tianjie.study.y2021.tranaop;

import com.tianjie.study.y2020.oracl.Lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TranUtil {

    private static final ThreadLocal<Map<String,Tlock>> LOCK = new ThreadLocal<Map<String, Tlock>>();

    public static synchronized void set(String lockID) {
        if(LOCK.get() ==null){
            LOCK.set(new ConcurrentHashMap<>());
        }
        LOCK.get().put(lockID,new Tlock(lockID));


    }

    public static Tlock get(String lockID) {
        Tlock tlock = null;
        if(null!=LOCK.get()){
            tlock = LOCK.get().get(lockID);
        }
        if(null==tlock){
            tlock = new Tlock(lockID);
            tlock.complete();
        }
        return tlock;
    }

    public static void complete(String id) {
        get(id).complete();
    }

    public static void remove(String id) {
        if(null!=LOCK.get()){
            LOCK.get().remove(id);
        }
    }
}
