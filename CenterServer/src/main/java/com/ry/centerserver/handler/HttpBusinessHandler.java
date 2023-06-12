package com.ry.centerserver.handler;

import com.ry.base.LogUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpBusinessHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        String content = String.format("Receive http erquest, uri:%s, method:%s, content:%s%n", fullHttpRequest.uri(), fullHttpRequest.method(), fullHttpRequest.content().toString(CharsetUtil.UTF_8));
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes()));
        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        LogUtil.i("content:" + content);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        LogUtil.i("channelActive:" + ctx.channel().remoteAddress().toString());

    }
}