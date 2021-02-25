package com.tianjie.study.y2021.io.socketrpc.rcpprovider;

import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.LittleBirdService;

public class LittleBirdServiceImpl implements LittleBirdService {
    @Override
    public String fly(String name) {
        return name + "飞啊飞";
    }
}
