package com.tianjie.study.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
@Aspect
@Component
public class ApectTest {

    @Pointcut("@annotation(getMapping)")
    public void pointCut(GetMapping getMapping){

    }

    @Around(value = "pointCut(getMapping)")
    public Object checkStart(ProceedingJoinPoint joinPoint, GetMapping getMapping) throws Throwable {
        System.out.println(1);
        return joinPoint.proceed();
    }
}
