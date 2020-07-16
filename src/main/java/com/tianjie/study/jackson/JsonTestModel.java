package com.tianjie.study.jackson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JsonTestModel {


    private String name;

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Integer age;

    @IsEnum("aa")
    private String sex;

    private BigDecimal money;
}
