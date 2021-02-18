package com.tianjie.study.y2020.bissafe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final int PORT = 8080;//监听的端口号

    private  static ExecutorService threadPool = Executors.newFixedThreadPool(5);
    //函数入口
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        while (true) {
            final Socket socket = server.accept();
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    String str = "";
                    try {
                        inputStream = socket.getInputStream();
                        outputStream = socket.getOutputStream();
                        int len = 0;
                        byte[] buf = new byte[65536];
                        while ((len = inputStream.read(buf)) != -1) {
                            str = new String(buf, 0, len);
                            System.out.println("服务端反转前说: " + str);
                            char[] char1 = str.toCharArray();
                            char[] char2 = new char[char1.length];
                            for (int i = 0, j = char2.length - 1; i < char1.length; i++, j--) {
                                char2[j] = char1[i];
                            }
                            String str1 = new String(char2);
                            System.out.println("服务端反转后说: " + str1);
                            outputStream.write(str1.getBytes());
                            outputStream.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            inputStream.close();
                            outputStream.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }

    }
}