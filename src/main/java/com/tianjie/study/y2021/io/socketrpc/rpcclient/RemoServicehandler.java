package com.tianjie.study.y2021.io.socketrpc.rpcclient;

import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.SocketRpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoServicehandler implements InvocationHandler {

    private String host;

    private int port;

    public RemoServicehandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("come in");
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
       try{
            Socket socket = new Socket(host,port);
            out = new ObjectOutputStream(socket.getOutputStream());
            SocketRpcRequest request = new SocketRpcRequest();

            request.setArgs(args);
            request.setClz(method.getDeclaringClass());
            request.setMethod(method.getName());
            out.writeObject(request);
            out.flush();
           in = new ObjectInputStream(socket.getInputStream());
           return in.readObject();

        }finally {
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        }
    }
}
