package com.tianjie.study.y2021.io.socketrpc.rcpprovider;

import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.LittleBirdService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RcpProvider {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        LittleBirdService littleBirdService = new LittleBirdServiceImpl();
        while (true){
            Socket accept = serverSocket.accept();
            executorService.submit(new SocketRpcHandler(accept,littleBirdService));

        }


    }
}
