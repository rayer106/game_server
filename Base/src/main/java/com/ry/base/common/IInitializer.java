package com.ry.base.common;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public abstract class IInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected abstract void initChannel(SocketChannel socketChannel);
}