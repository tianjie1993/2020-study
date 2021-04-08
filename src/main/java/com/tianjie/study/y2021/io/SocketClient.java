package com.tianjie.study.y2021.io;

import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.SocketRpcRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Optional;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Optional<String> aa = Optional.ofNullable("ad");
        System.out.println(aa.get());
//        String host = "127.0.0.1";
//        String port = "8888";
//        Socket socket = new Socket(host, Integer.parseInt(port));
//        OutputStream outputStream = socket.getOutputStream();
////        for (int i = 0; i < 10; i++) {
////            outputStream.write(("你好啊"+i).getBytes());
////        }
//        SocketRpcRequest request = new SocketRpcRequest();
//        request.setMethod("adsfd");
//        new ObjectOutputStream(outputStream).writeObject(request);
//        outputStream.close();
//        socket.close();
    }
}
