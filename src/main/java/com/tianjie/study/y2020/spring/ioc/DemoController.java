package com.tianjie.study.y2020.spring.ioc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@RestController
public class DemoController {

    @Autowired
    private TestService testService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/getname")
    public String getName(HttpServletRequest request, HttpServletResponse response) throws IntrospectionException, JsonProcessingException {
        return objectMapper.writeValueAsString(testService.getName("a"));
    }
}
