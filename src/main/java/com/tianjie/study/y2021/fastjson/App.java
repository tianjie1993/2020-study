package com.tianjie.study.y2021.fastjson;

import com.alibaba.fastjson.JSON;
import com.tianjie.study.model.LittleBird;

public class App {

    public static void main(String[] args) {
        LittleBird littleBird = new LittleBird();
        littleBird.setName("01");
        littleBird.setKind(BirdKindEnum.SPARROW.code());
        String jsonStr = JSON.toJSONString(littleBird);
        System.out.println(jsonStr);
    }
}
