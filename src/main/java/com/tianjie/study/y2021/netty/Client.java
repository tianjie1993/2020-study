package com.tianjie.study.y2021.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.ThreadPoolExecutor;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        //线程池 默认是cup* 2
        EventLoopGroup group = new NioEventLoopGroup();
        //辅助启动类
        Bootstrap b = new Bootstrap();
        try {
            ChannelFuture cf = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8888)
                    .addListener(new Alistner())
                    .sync();// 需要知道是否连接成功时使用 阻塞等待结束

            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ClientHandler());
    }
}

class ClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = null;
        try {
            byteBuf= (ByteBuf) msg;
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            System.out.println(new String(bytes));
            System.out.println(byteBuf.refCnt());
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = Unpooled.copiedBuffer("tianjie".getBytes());
        ctx.writeAndFlush(byteBuf);
    }
}

class Alistner implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if(channelFuture.isSuccess()){
            System.out.println("连上了");
        }else{
            System.out.println("没连上");
        }
    }
}
