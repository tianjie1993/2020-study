package com.tianjie.study.y2020.json.fastjson;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.ValueFilter;

public class MyBeforeFilter implements ValueFilter {


    @Override
    public Object process(Object o, String key, Object value) {
        String text = value + "元"+"\",\""+key+"name"+"\":\""+123;
        System.out.println("\\");
        return text;
    }
}
