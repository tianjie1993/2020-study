package com.tianjie.study.y2021.io.socketrpc.rcpprovider;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RcpProvider {

    private final YrRpcServiceContext context;


    public RcpProvider(YrRpcServiceContext context) {
        this.context = context;
    }


    @PostConstruct
    public void publish() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()->{
            while (true){
                Socket accept = serverSocket.accept();
                executorService.submit(new SocketRpcHandler(accept,context));

            }
        });
    }

}
