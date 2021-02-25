package com.tianjie.study.y2021.io.socketrpc.rcpserviceapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocketRpcRequest implements Serializable {

    private String method;

    private Class clz;

    private Object[] args;
}
