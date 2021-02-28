package com.tianjie.study.y2021.io.socketrpc.rcpserviceapi;

import com.tianjie.study.y2021.io.socketrpc.annotation.YrRpcClient;

@YrRpcClient(host = "127.0.0.1",port = 9000)
public interface LittleBirdService {

    String fly(String name);

}
