package com.ry.centerserver.handler;

import com.ry.base.LogUtil;
import com.ry.base.server.protocol.tcp.message.cs.TcpC2SEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;

public class TcpBusinessHandler extends SimpleChannelInboundHandler<TcpC2SEntity> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        LogUtil.i("channelActive:" + insocket.getAddress().getHostAddress());
        //ctx.channel().close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        LogUtil.i("channelInactive:" + insocket.getAddress().getHostAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TcpC2SEntity tcpCSEntity) {
        LogUtil.i("现在进入到TcpBusinessHandler.channelRead0");
    }
}