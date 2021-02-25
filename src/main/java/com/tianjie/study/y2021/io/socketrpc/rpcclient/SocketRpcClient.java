package com.tianjie.study.y2021.io.socketrpc.rpcclient;

import com.tianjie.study.model.LittleBird;
import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.LittleBirdService;

import java.lang.reflect.Proxy;

public class SocketRpcClient {

    public static void main(String[] args) {

        LittleBirdService littleBirdService = getRemoteService(LittleBirdService.class,9000);
        String adfasdfa = littleBirdService.fly("凤凰");
        System.out.println(adfasdfa);
    }



    private static <T> T getRemoteService(Class<LittleBirdService> littleBirdServiceClass, int i) {
        Object service = Proxy.newProxyInstance(LittleBirdService.class.getClassLoader(), new Class[]{LittleBirdService.class}, new RemoServicehandler("localhost", i));
        return (T) service;
    }
}
