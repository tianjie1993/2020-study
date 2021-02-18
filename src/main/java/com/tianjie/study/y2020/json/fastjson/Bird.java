package com.tianjie.study.y2020.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

@Data

public  class Bird {

    @JSONField(serializeUsing = ModelValueSerializer.class)
    private String name;

}
