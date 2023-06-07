package com.ry.base.server.protocol.tcp.message.cs;

public enum MessageCSType {
    Normal(1, "请求-应答"),
    Notification(2, "通知类型"),
    Broadcast(3,"广播类型"),
    Keep_Alive(99,"心跳类型");

    private int messageType;

    MessageCSType(int messageType, String messageDesc) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public static MessageCSType fromMessageId(int messageType) {
        for (MessageCSType type : MessageCSType.values()) {
            if (type.getMessageType() == messageType) {
                return type;
            }
        }
        return null;
    }
}
