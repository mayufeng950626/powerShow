package com.mayufeng.common;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现客户端发送一个请求，服务器会返回hello netty
 */
public class Netty {
    public static void main(String[] args) throws InterruptedException {
        // 定义一对主从线程组
        // 主线程组,用于接受客户端的连接，但是不做任何处理，跟老板一样，不做事
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程组，老板线程组会把任务丢给他，让手下线程组做任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // netty服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            /**
             group是定义主从线程组
             channel代表双向管道使用的Nio
             childHandler是定义助手类
             */
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new HelloServerInitializer());
            // 启动server，并且设置8080为启动的端口，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            // 监听关闭的channel，设置为同步方式关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
