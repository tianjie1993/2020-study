package com.tianjie.study.y2020.jackson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TestJsonController {

    @GetMapping("/jsontest")
    public Object test(){
        JsonTestModel testModel = new JsonTestModel();
        testModel.setAge(27);
        testModel.setSex("1");
        testModel.setName("zhagnsan");
        testModel.setMoney(new BigDecimal("12312.12312312"));
        return testModel;
    }
}
