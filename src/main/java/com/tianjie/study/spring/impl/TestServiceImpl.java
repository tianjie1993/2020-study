package com.tianjie.study.spring.impl;

import com.tianjie.study.spring.ioc.TestService;
import org.springframework.stereotype.Service;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
//@TJService
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String getName() {
        return "wo de tian a ";
    }
}
