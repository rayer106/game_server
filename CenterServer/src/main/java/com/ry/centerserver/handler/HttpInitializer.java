package com.ry.centerserver.handler;

import com.ry.base.server.protocol.http.HttpServerInitializer;
import io.netty.channel.socket.SocketChannel;

public class HttpInitializer extends HttpServerInitializer {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        super.initChannel(socketChannel);
        socketChannel.pipeline().addLast(new HttpBusinessHandler());
    }
}
