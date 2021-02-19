package com.tianjie.study.y2020.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

import java.util.HashMap;
import java.util.Map;

public class FastJsonTest {

    public static void main(String[] args) {

        Bird a = new Bird();
        a.setName("ad");

        SerializeFilter filter = new MyBeforeFilter();
        Map<String,Object>  dad = new HashMap<>();
        dad.put("bird",a);
        dad.put("ad","asd");
        String json = JSON.toJSONString(a,filter);
        Object parsejSOn = JSON.parse(json);
        System.out.println(json);
    }

}
