package com.tianjie.study.y2021.filesystem;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Condition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@ComponentScan
public class AspectPluts {

    @Pointcut("@annotation(getMapping)")
    public void pointCut(Controller getMapping){

    }

}
