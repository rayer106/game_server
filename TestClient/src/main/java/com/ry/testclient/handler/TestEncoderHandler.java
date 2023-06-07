package com.ry.testclient.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

public class TestEncoderHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        byte[] message = msg.toString().getBytes(StandardCharsets.UTF_8);
        byte[] token = "这是一段token，需要服务器进行解析并鉴权".getBytes(StandardCharsets.UTF_8);
        int tokenLength = token.length;
        int client_version = 1;
        int client_type = 1;

        int length = message.length + 12 + tokenLength;

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(length + 4);
        byteBuf.writeInt(length);
        byteBuf.writeInt(client_version);
        byteBuf.writeInt((client_type));
        byteBuf.writeInt(tokenLength);
        byteBuf.writeBytes(token);
        byteBuf.writeBytes(message);

        System.out.println("Thread1:" + Thread.currentThread().getName() + ", write:发送给服务器");
        ctx.writeAndFlush(byteBuf);
    }
}
