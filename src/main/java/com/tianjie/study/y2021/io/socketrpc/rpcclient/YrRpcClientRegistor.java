package com.tianjie.study.y2021.io.socketrpc.rpcclient;

import com.tianjie.study.y2021.io.socketrpc.annotation.RpcAutowried;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class YrRpcClientRegistor implements BeanPostProcessor {



    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        String basePackage = "com.tianjie.study";
        if(!bean.getClass().getName().startsWith(basePackage)){
            return bean;
        }
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcAutowried rpcAutowried = declaredField.getAnnotation(RpcAutowried.class);
            if(null==rpcAutowried){
                continue;
            }
            Object proxyClent = RpcClientProxyFactory.getProxyClent(declaredField.getType());
            declaredField.setAccessible(true);
            declaredField.set(bean,proxyClent);

        }

        return bean;
    }
}
