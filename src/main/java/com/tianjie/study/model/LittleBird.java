package com.tianjie.study.model;

import com.alibaba.fastjson.annotation.JSONType;
import com.tianjie.study.y2021.fastjson.BirdKindEnum;
import com.tianjie.study.y2021.fastjson.YrEnumAfterFilter;
import com.tianjie.study.y2021.fastjson.YrField;
import lombok.Data;

@Data
@JSONType(serialzeFilters = {YrEnumAfterFilter.class})
public class LittleBird {

    private String name;

    @YrField(key="BirdKindEnum",enumClasses = {BirdKindEnum.class})
    private String kind;


}
