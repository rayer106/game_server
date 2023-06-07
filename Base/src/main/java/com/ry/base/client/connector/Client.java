package com.ry.base.client.connector;

import com.ry.base.LogUtil;
import com.ry.base.common.IInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public abstract class Client {

    IInitializer initializer;
    static EventLoopGroup workGroup = new NioEventLoopGroup();

    public Client() {
    }

    public Client initializer(IInitializer initializer) {
        this.initializer = initializer;
        return this;
    }

    public abstract IInitializer getDefaultInitializer();

    public void connect(String ip, int port) throws Exception {
        if (initializer == null) {
            initializer = getDefaultInitializer();
            if (initializer == null) {
                throw new Exception("no initialize defined");
            }
        }

        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(initializer);
            LogUtil.i("Tcp client connecting");
            ChannelFuture channelFuture = b.connect(ip, port).sync();
            channelFuture.addListener(cf -> {
                if (cf.isSuccess()) {
                    LogUtil.i("服务器连接成功，ip:" + ip + ":" + port + "");
                } else {
                    LogUtil.i("服务器连接失败，ip:" + ip + ":" + port + "");
                }
            });
            channelFuture.channel().closeFuture().addListener(cf -> {
                channelFuture.channel().close();
                LogUtil.i("服务器断开连接");
            });
        } catch (InterruptedException e) {
            workGroup.shutdownGracefully();
        }
    }
}