package com.tianjie.study.y2021.tranaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    TestService testService;

    @GetMapping("/tranaop")
    public Object test() throws InterruptedException {
        testService.test();
        System.out.println("主线程事务提交成功");
        return "success";
    }
}
