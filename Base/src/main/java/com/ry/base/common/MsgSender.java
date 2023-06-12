package com.ry.base.common;

import com.ry.base.proto.impl.*;

public class MsgSender {

    public static Message buildMessage(MessageID msgId) {
        MessageHeader.Builder msgHeaderBuilder = MessageHeader.newBuilder();
        msgHeaderBuilder.setMsgId(msgId)
                .setMsgType(MessageType.Response)
                .setVersion(String.valueOf(1));

//        .Builder loginReq = LoginRequest.newBuilder();
//        loginReq.setPhone("123456")
//                .setVerifyCode("123456");

        //MessageBody.Builder msgBodyBuilder = MessageBody.newBuilder();
        //msgBodyBuilder.setLoginReq(data);

        Message.Builder builder = Message.newBuilder();
        builder.setMsgHeader(msgHeaderBuilder);

        //builder.setMsgBody(msgBodyBuilder);
        return builder.build();
    }

    public static Message buildMessage(MessageID msgId, MessageType type) {
        MessageHeader.Builder msgHeaderBuilder = MessageHeader.newBuilder();
        msgHeaderBuilder.setMsgId(msgId)
                .setMsgType(MessageType.Response)
                .setVersion(String.valueOf(1));

        Message.Builder builder = Message.newBuilder();
        builder.setMsgHeader(msgHeaderBuilder);

        return builder.build();
    }

    public static Message buildMessage(MessageID msgId, MessageType messageType, LoginRequest.Builder loginReq) {
        MessageHeader.Builder msgHeaderBuilder = MessageHeader.newBuilder();
        msgHeaderBuilder.setMsgId(msgId)
                .setMsgType(messageType)
                .setVersion(String.valueOf(1));

//        .Builder loginReq = LoginRequest.newBuilder();
//        loginReq.setPhone("123456")
//                .setVerifyCode("123456");

        MessageBody.Builder msgBodyBuilder = MessageBody.newBuilder();
        msgBodyBuilder.setLoginReq(loginReq);

        Message.Builder builder = Message.newBuilder();
        builder.setMsgHeader(msgHeaderBuilder);
        builder.setMsgBody(msgBodyBuilder);

        return builder.build();
    }
}
