package com.tianjie.study.y2021.filesystem;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Condition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
public class AspectPluts {

//    @Pointcut("@annotation(getMapping)")
//    public void pointCut(GetMapping getMapping){
//
//    }

    @Before("@annotation(getMapping)")
    public void before(GetMapping getMapping){
        System.out.println("Before");

    }

    @After("@annotation(getMapping)")
    public void after(GetMapping getMapping){
        System.out.println("After");

    }
    @AfterReturning("@annotation(getMapping)")
    public void afterReturning(GetMapping getMapping){
        System.out.println("AfterReturning");

    }

}
