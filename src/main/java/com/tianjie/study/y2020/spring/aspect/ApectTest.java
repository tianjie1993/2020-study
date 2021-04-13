package com.tianjie.study.y2020.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 *
 * @author tian.jie
 * @version 1.0
 * @email tian.jie@rongzer.com
 * @date 2020/5/25 20:25
 */
//@Aspect
//@Component
public class ApectTest {

    @Pointcut("@annotation(getMapping)")
    public void pointCut(GetMapping getMapping){

    }

    @Pointcut("execution(* com.tianjie.study.y2020.spring.impl.TestServiceImpl.getName(..))")
    public void pointCut1(){

    }

    @Before("pointCut1()")
    public void excute1(){
        System.out.println(2212);
    }

    @Around(value = "pointCut(getMapping)")
    public Object checkStart(ProceedingJoinPoint joinPoint, GetMapping getMapping) throws Throwable {
        return joinPoint.proceed();
    }
}
