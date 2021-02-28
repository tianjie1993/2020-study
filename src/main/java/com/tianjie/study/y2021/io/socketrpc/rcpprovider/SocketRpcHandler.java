package com.tianjie.study.y2021.io.socketrpc.rcpprovider;

import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.SocketRpcRequest;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;


public class SocketRpcHandler implements Runnable {

    private Socket socket;

    private YrRpcServiceContext context;


    public SocketRpcHandler(Socket socket, YrRpcServiceContext context) {
        this.socket = socket;
        this.context = context;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("接受到了请求");
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try{
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            SocketRpcRequest request = (SocketRpcRequest) in.readObject();
            Object result = handler(request);
            out.writeObject(result);
            out.flush();
            socket.close();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }

        }


    }

    private Object handler(SocketRpcRequest request) {
        Class clz = request.getClz();
        String method = request.getMethod();
        Object []args = request.getArgs();
        Class<?> [] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        try {
            Method m = context.getService(clz.getName()).getClass().getMethod(method,types);
            return m.invoke(context.getService(clz.getName()),args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
