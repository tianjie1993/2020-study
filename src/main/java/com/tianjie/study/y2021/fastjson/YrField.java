package com.tianjie.study.y2021.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.tianjie.study.common.YrBaseEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记枚举字段
 *
 * @author tian.jie
 * @version 1.0
 * @date 2021-02-19 21:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface YrField {

    String key();

    Class<? extends YrBaseEnum> [] enumClasses() default  {};

}
