package com.tianjie.study.y2020.json.fastjson;

import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class MyAfterFilter extends AfterFilter {
    @Override
    public void writeAfter(Object o) {
        System.out.println(1);
    }
}
