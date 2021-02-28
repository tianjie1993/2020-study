package com.tianjie.study.y2021.io.socketrpc.rpcclient;

import com.tianjie.study.y2021.io.socketrpc.annotation.RpcAutowried;
import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.LittleBirdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpcClientController {

    private LittleBirdService littleBirdService;

    public RpcClientController() {
        this.littleBirdService = RpcClientProxyFactory.getProxyClent(LittleBirdService.class);
    }

    @GetMapping("/testRpcClinet/{birdname}")
    public Object RpcClientTest(@PathVariable String birdname){
        return littleBirdService.fly(birdname);
    }
}
