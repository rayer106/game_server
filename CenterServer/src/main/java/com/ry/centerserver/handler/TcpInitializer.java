package com.ry.centerserver.handler;

import com.ry.base.server.protocol.tcp.handler.TcpC2SInitializer;
import io.netty.channel.socket.SocketChannel;

public class TcpInitializer extends TcpC2SInitializer {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        super.initChannel(socketChannel);
        socketChannel.pipeline().addLast(new TcpBusinessHandler());
    }
}
