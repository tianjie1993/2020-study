package com.tianjie.study.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@JsonComponent
public class JosnExt extends JsonSerializer<BigDecimal> {
    //可进行全局处理，例如对所有 bigdecimal 保留两位等

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        System.out.println(value);
        gen.writeString(value.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString());
    }
}
