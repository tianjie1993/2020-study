package com.tianjie.study.y2021.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) throws IOException, InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        ServerBootstrap b  = new ServerBootstrap();
        try {
            ChannelFuture sync = b.group(group, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            excute(ch);
                        }


                    })
                    .bind(8888).sync();
            sync.channel().closeFuture().sync();//没人调用close ,则一直阻塞，需要有人关闭
        }catch (Exception e){

        }finally {
            group.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    private static void excute(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ServerChildHandler());
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.channels.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = null;
        try {
            byteBuf= (ByteBuf) msg;
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            System.out.println("接收到"+new String(bytes));
            ByteBuf byteBufs = Unpooled.copiedBuffer("主机返回".getBytes());
            Server.channels.writeAndFlush(byteBufs);
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }
}
