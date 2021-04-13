package com.tianjie.study.y2021.filesystem.controller;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Controller
public class HomeController {

    @RequestMapping({ "/" })
    public String home() {
        return "home";
    }


}
