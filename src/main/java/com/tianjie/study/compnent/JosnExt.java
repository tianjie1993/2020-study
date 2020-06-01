package com.tianjie.study.compnent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@JsonComponent
public class JosnExt extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        System.out.println(value);
    }
}
