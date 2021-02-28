package com.tianjie.study.y2021.io.socketrpc.rcpprovider;

import com.tianjie.study.y2021.io.socketrpc.annotation.YrRpcService;
import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.LittleBirdService;

@YrRpcService(publisher = LittleBirdService.class)
public class LittleBirdServiceImpl implements LittleBirdService {
    @Override
    public String fly(String name) {
        return name + "飞啊飞";
    }
}
