package com.tianjie.study.y2021.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        String port = "8888";
        Socket socket = new Socket(host, Integer.parseInt(port));
        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 10; i++) {
            outputStream.write(("你好啊"+i).getBytes());
        }
        outputStream.close();
        socket.close();
    }
}
