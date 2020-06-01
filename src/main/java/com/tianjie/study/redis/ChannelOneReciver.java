package com.tianjie.study.redis;

import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
//@Component
public class ChannelOneReciver {

    public void receiveMessage(Object message){
        System.out.println(message);
    }
}
