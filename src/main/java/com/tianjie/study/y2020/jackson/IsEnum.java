package com.tianjie.study.y2020.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(
        using = EnumSerializer.class
)
public @interface IsEnum {

    /**
     * 对应 系统服务中的r_dict_biz 表中的code 字段
     * 根据code 和 字段的值定位 唯一码值
     */
    String value() ;

}