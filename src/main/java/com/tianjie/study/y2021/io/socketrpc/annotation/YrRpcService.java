package com.tianjie.study.y2021.io.socketrpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Component
public @interface YrRpcService {

    Class publisher();
}
