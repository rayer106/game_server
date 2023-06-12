package com.ry.testclient.handler;

import com.ry.base.common.MsgSender;
import com.ry.base.proto.impl.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;

public class TestHandler extends SimpleChannelInboundHandler<Message> {
    //===================================================================
    //|消息体长度|消息ID|消息类型|token长度|token|数据内容|
    //|4字节    |4字节 |4字节  |4字节    |
    //===================================================================
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message o) throws Exception {
        System.out.println("Thread1:" + Thread.currentThread().getName() + ", channelRead0:收到服务器消息, msg id:" + o.getMsgHeader().getMsgId());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println("Thread1:" + Thread.currentThread().getName() + ", channelActive:" + insocket.getAddress().getHostAddress());

        System.out.println("Thread1:" + Thread.currentThread().getName() + ", channelActive:写入并启动发送流程");
//        for (int i = 0; i < 100; i++) {
//            StringBuilder sb = new StringBuilder("这里是消息的主体，需要发送给服务端");
//            ctx.channel().writeAndFlush(sb.toString());
        //ctx.channel().writeAndFlush("\n\r========================" + i + "============================");

//            try {
//                Thread.sleep(0);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }


//        MessageHeader.Builder msgHeaderBuilder = MessageHeader.newBuilder();
//        msgHeaderBuilder.setMsgId(MessageID.LoginReq)
//                .setMsgType(MessageType.Request)
//                .setVersion(String.valueOf(1));

        LoginRequest.Builder loginReq = LoginRequest.newBuilder();
        loginReq.setPhone("123456")
                .setVerifyCode("123456");

//        MessageBody.Builder msgBodyBuilder = MessageBody.newBuilder();
//        msgBodyBuilder.setLoginReq(loginReq);
//
//        Message.Builder builder = Message.newBuilder();
//        builder.setMsgHeader(msgHeaderBuilder);
//        builder.setMsgBody(msgBodyBuilder);

        ctx.channel().writeAndFlush(MsgSender.buildMessage(MessageID.LoginReq, MessageType.Request, loginReq));

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println("Thread1:" + Thread.currentThread().getName() + ", channelInactive:" + insocket.getAddress().getHostAddress());
    }
}
