package com.tianjie.study.y2021.io.socketrpc.rpcclient;

import com.tianjie.study.y2021.io.socketrpc.annotation.YrRpcClient;

import java.lang.reflect.Proxy;

public class RpcClientProxyFactory {

    public static <T> T getProxyClent(Class<T> interfaceClz){
        if(!interfaceClz.isInterface()){
            throw new RuntimeException("不是远程调用类");
        }
        YrRpcClient rpcClient = interfaceClz.getAnnotation(YrRpcClient.class);
        Object service = Proxy.newProxyInstance(interfaceClz.getClassLoader(), new Class[]{interfaceClz}, new RemoServicehandler(rpcClient.host(), rpcClient.port()));
        return (T) service;

    }

}
