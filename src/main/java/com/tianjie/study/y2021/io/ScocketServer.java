package com.tianjie.study.y2021.io;

import com.tianjie.study.model.LittleBird;
import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.SocketRpcRequest;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ScocketServer {

    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws IOException {
        int port = 8888;

        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            Socket accept = serverSocket.accept();
            Runnable runnable = new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println("接受到请求");
                    InputStream inputStream = accept.getInputStream();
                    ObjectInputStream inputStream1 = new ObjectInputStream(inputStream);
                    SocketRpcRequest request = (SocketRpcRequest) inputStream1.readObject();
//                    byte []bytes =new byte[1024];
//                    int len;
//                    StringBuilder stringBuilder = new StringBuilder();
//                    while((len=inputStream.read(bytes))!=-1){
//                        stringBuilder.append(new String(bytes,0,len, "UTF-8"));
//                    }
                    System.out.println("接受消息"+request.getMethod());
                    inputStream.close();;
                    accept.close();
                }
            };
            threadPoolExecutor.submit(runnable);
        }

    }
}
