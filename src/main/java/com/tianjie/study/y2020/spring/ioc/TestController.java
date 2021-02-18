package com.tianjie.study.y2020.spring.ioc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@TJController
public class TestController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;

    @RequestMapping("/getname")
    public String getName(HttpServletRequest request, HttpServletResponse response) throws IntrospectionException, JsonProcessingException {
        return objectMapper.writeValueAsString(testService.getName("a"));
    }
}
