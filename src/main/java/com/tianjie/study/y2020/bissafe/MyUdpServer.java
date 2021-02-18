package com.tianjie.study.bissafe;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MyUdpServer {

    DatagramSocket server = null;
    DatagramPacket packet = null;
    byte[] b =null;


    public static void main(String[] args) {
        MyUdpServer server = new MyUdpServer(8080);
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                server.receiveData();
            }

        });

        t.start();
    }


    public MyUdpServer(int port){
        super();
        try {
            server = new DatagramSocket(port);
            System.out.println("UDP服务端已启动，正在监听端口"+port);
        } catch (SocketException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public void receiveData(){
        b = new byte[1024];
        int len=0;
        packet = new DatagramPacket(b,b.length);
        try {
            while (true){
                server.receive(packet);
                if ( (len=packet.getLength()) >0 ){
                    String msg = new String(packet.getData(),0,len);
                    InetAddress ip = packet.getAddress();
                    System.out.println("来自主机"+ip + "的消息:" + msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}