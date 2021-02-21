package com.tianjie.study.y2021.fastjson;

import com.alibaba.fastjson.serializer.AfterFilter;
import com.tianjie.study.common.YrBaseEnum;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * 实体类序列化结束后进行写枚举项
 *
 * @author tian.jie
 * @version 1.0
 * @date 2021-02-19 21:14
 */
@Slf4j
public class YrEnumAfterFilter extends AfterFilter {

    @Override
    public void writeAfter(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                YrField yrField = field.getAnnotation(YrField.class);
                if (yrField == null) {
                    continue;
                }
                field.setAccessible(true);
                Object dictCode = field.get(object);
                //查询码值含义
                this.writeKeyValue(field.getName() + "Name", getEnumValue(yrField, dictCode));

            }
        } catch (Exception e) {
            log.warn("添加码值错误", e);
        }

    }

    /**
     * 获取码值含义
     *
     * @author tian.jie
     * @version 1.0
     * @date 2021-02-19 21:21
     */
    private Object getEnumValue(YrField yrField, Object dictCode) {
        for (Class<? extends YrBaseEnum> aClass : yrField.enumClasses()) {
            if (!aClass.isEnum()) {
                continue;
            }
            for (YrBaseEnum enumConstant : aClass.getEnumConstants()) {
                if (enumConstant.code().equals(String.valueOf(dictCode))) {
                    return enumConstant.value();
                }
            }
        }


        return getDictNameByGoupAndCode(yrField.key(), String.valueOf(dictCode));
    }

    /**
     * 根据group 和code 获取name
     * 实际可以从数据库或者缓存中获取
     *
     * @author tian.jie
     * @version 1.0
     * @date 2021-02-19 21:26
     */
    private Object getDictNameByGoupAndCode(String key, String valueOf) {
        return "test";
    }
}
