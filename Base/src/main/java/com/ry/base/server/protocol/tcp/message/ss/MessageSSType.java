package com.ry.base.server.protocol.tcp.message.ss;

public enum MessageSSType {
    Normal(1, "请求-应答"),
    Notification(2, "通知类型"),
    Broadcast(3,"广播类型"),
    Keep_Alive(99,"心跳类型");

    private int messageType;

    MessageSSType(int messageType, String messageDesc) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public static MessageSSType fromMessageId(int messageType) {
        for (MessageSSType type : MessageSSType.values()) {
            if (type.getMessageType() == messageType) {
                return type;
            }
        }
        return null;
    }
}
