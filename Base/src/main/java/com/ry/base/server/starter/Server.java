package com.ry.base.server.starter;

import com.ry.base.LogUtil;
import com.ry.base.MessageEvent;
import com.ry.base.common.IInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public abstract class Server implements MessageEvent.Listener {

    String ip;
    int port;
    IInitializer initializer;
    static EventLoopGroup bossGroup = new NioEventLoopGroup();
    static EventLoopGroup workerGroup = new NioEventLoopGroup();

    public Server() {
    }

    public Server ip(String ip) {
        this.ip = ip;
        return this;
    }

    public Server port(int port) {
        this.port = port;
        return this;
    }

    public Server initializer(IInitializer initializer) {
        this.initializer = initializer;
        return this;
    }

    public abstract IInitializer getDefaultInitializer();

    public void start() throws Exception {
        if (initializer == null) {
            initializer = getDefaultInitializer();
            if (initializer == null) {
                throw new Exception("no initialize defined");
            }
        }

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(initializer);
            LogUtil.i("Server starting");
            ChannelFuture channelFuture = b.bind(new InetSocketAddress(port)).sync();
            channelFuture.addListener(cf -> {
                if (cf.isSuccess()) {
                    LogUtil.i("server 启动成功，端口：{" + port + "}");
                } else {
                    LogUtil.i("server 启动失败，端口：{" + port + "}");
                }
            });
            LogUtil.i("Server started， Listening on " + port);
            channelFuture.channel().closeFuture().addListener(cf -> {
                channelFuture.channel().close();
                LogUtil.i("Server closed");
            });
        } catch (InterruptedException e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void onEvent(int event, Object message) {
        LogUtil.i("TCP服务器启动后的通知");
    }
}
