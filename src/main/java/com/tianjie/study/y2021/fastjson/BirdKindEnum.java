package com.tianjie.study.y2021.fastjson;

import com.tianjie.study.common.YrBaseEnum;

public enum BirdKindEnum implements YrBaseEnum<String,String> {


    SPARROW("sparrow","麻雀"),
    PHOENIX("Phoenix","凤凰"),

    ;

    private String code;

    private String value;

    BirdKindEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String value() {
        return this.value;
    }
}
