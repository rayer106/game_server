package com.ry.base.server.protocol.tcp.message.cs;

public class TcpS2CEntityHeader {
    private int messageId;
    private int messageType;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}