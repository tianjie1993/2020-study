package com.tianjie.study.y2020.mysql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@Service
public class UserService extends ServiceImpl<UserMapper, TUser> {


    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void test(){
        userMapper.getById(1);
    }
}
