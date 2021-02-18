package com.tianjie.study.y2020.spring.cycledepend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@Service
public class B {

    @Autowired
    private A a;
}
