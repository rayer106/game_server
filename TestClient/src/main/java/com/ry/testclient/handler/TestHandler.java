package com.ry.testclient.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;

public class TestHandler extends SimpleChannelInboundHandler {
    //===================================================================
    //|消息体长度|消息ID|消息类型|token长度|token|数据内容|
    //|4字节    |4字节 |4字节  |4字节    |
    //===================================================================
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println("Thread1:" + Thread.currentThread().getName() + ", channelActive:" + insocket.getAddress().getHostAddress());

        System.out.println("Thread1:" + Thread.currentThread().getName() + ", channelActive:写入并启动发送流程");
        ctx.channel().writeAndFlush("这里是消息的主体，需要发送给服务端");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println("Thread1:" + Thread.currentThread().getName() + ", channelInactive:" + insocket.getAddress().getHostAddress());
    }
}
