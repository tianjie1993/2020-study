package com.tianjie.study.y2021.io.nio.client;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.alibaba.fastjson.JSON;
import com.tianjie.study.model.LittleBird;
import com.tianjie.study.y2021.fastjson.BirdKindEnum;
import org.drools.core.metadata.Lit;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class NioClient {
    // 端口号
    private static final int PORT = 8888;
    // 缓存大小
    private static final int BUFFER_SIZE = 1024;
    // 控制台输入流
    private static Scanner scanner = new Scanner(System.in);

    public static Object getChannlValue(int port) {
        return channleValue.get(port);
    }

    public void send() throws IOException {
        // 初始化客户端
        System.out.println("Initialize client...");
        // 1. 打开通道
        SocketChannel socketChannel = SocketChannel.open();
        // 2. 设置为非阻塞式
        socketChannel.configureBlocking(false);
        // 3. 连接服务器
        socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
        // 4. 创建选择器
        Selector selector = Selector.open();
        // 5. 绑定连接事件
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        // 与服务器建立连接
        // 若selector未被打开，则终止连接
        while (selector.isOpen()) {
            // 查询可用通道
            selector.select();
            // 1. 获得channel的迭代器
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 2. 遍历迭代器
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 3. 获得该通道后，就将其从迭代器中删除
                iterator.remove();
                // 4. 根据不同的通道进行不同的业务
                if (key.isConnectable()) {
                    System.out.println("connect with server...");
                    // 连接业务
                    connect(selector,key);
                }
                if (key.isValid() && key.isReadable()) {
                    System.out.print("receive from server: ");
                    // 读取业务
                    read(selector,key);
                    socketChannel.close();
                    return ;
                }
                if (key.isValid() && key.isWritable()) {
                    System.out.print("send to server: ");
                    // 写出业务
                    write(selector,key);
                }
            }
        }
    }

    /**
     * 连接业务
     * @param selector
     * @param key
     */
    private void connect(Selector selector, SelectionKey key) throws IOException {
        // 1. 获得TCP协议通信的通道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 完成连接
        socketChannel.finishConnect();
        // 连接成功
        if (socketChannel.isConnected()) {
            System.out.println("connect success!");
        }
        // 2. 通道设为非阻塞式
        socketChannel.configureBlocking(false);
        // 3. 绑定写出事件，向服务器写出信息
        socketChannel.register(selector,SelectionKey.OP_WRITE);
    }

    /**
     * 写出业务
     * @param selector
     * @param key
     */
    private void write(Selector selector, SelectionKey key) throws IOException {
        // 1. 获得TCP协议通信的通道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.configureBlocking(false);
        LittleBird a = new LittleBird();
        a.setName("小小");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(bout);
        obj.writeObject(a);
        obj.flush();
        byte[] bytes = bout.toByteArray();
        ByteBuffer writebuff = ByteBuffer.wrap(bytes);
        socketChannel.write(writebuff);
//        // 2. 分配缓存区
//        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
//        // 3. 清空缓存区
//        buffer.clear();
//        // 4. 写入发送的信息
//        buffer.put(ByteBuffer.wrap(scanner.nextLine().getBytes(StandardCharsets.UTF_8)));
//        // 5. 将缓存区的指针回到初始位置
//        buffer.flip();
//        // 当通道连接成功时
//        if (socketChannel.isConnected()) {
//            // 6. 设置通道为非阻塞式
//            socketChannel.configureBlocking(false);
//            // 7. 不断写出到通道
//            while (buffer.hasRemaining()) {
//                socketChannel.write(buffer);
//            }
            // 8. 继续注册读取事件
            socketChannel.register(selector,SelectionKey.OP_READ);
//        }
    }

    private static  Map<Integer,Object> channleValue = new ConcurrentHashMap<>();

    /**
     * 读取业务
     * @param selector
     * @param key
     */
    public static void read(Selector selector, SelectionKey key) throws IOException {
        // 1. 获得TCP协议通信的通道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 2. 分配缓存区
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        // 创建字节数组
        byte[] bytes = new byte[BUFFER_SIZE * 2];
        // 设置下标
        int index = 0;
        // 3. 读取数据到缓冲区
        while (socketChannel.read(buffer) != 0) {
            // 4. 将缓存区的指针回到初始位置
            buffer.flip();
            // 5. 不断读出数据
            while (buffer.hasRemaining()) {
                bytes[index++] = buffer.get();
            }
            // 读取完毕后，清空缓冲区
            buffer.clear();
        }
        LittleBird  bird = (LittleBird) toObject(bytes);

        System.out.println(JSON.toJSONString(bird));
        channleValue.put(socketChannel.socket().getPort(),bird);
        // 6. 注册写出业务
        socketChannel.register(selector,SelectionKey.OP_WRITE);
    }


    public static Object toObject(byte[] buff) {
        ByteArrayInputStream bis = new ByteArrayInputStream(buff);
        ObjectInputStream ois = null;
        try {
            //包装字节数组流
            ois = new ObjectInputStream(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object object = null;
        try {
            //读入对象
            object = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
    public static void main(String[] args) throws IOException {
        new NioClient().send();
    }
}
