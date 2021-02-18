package com.tianjie.study.y2020.json.fastjson;

import com.alibaba.fastjson.JSON;

public class FastJsonTest {

    public static void main(String[] args) {

        Bird a = new Bird();
        a.setName("ad");

        String json = JSON.toJSONString(a);
        System.out.println(json);
    }

}
