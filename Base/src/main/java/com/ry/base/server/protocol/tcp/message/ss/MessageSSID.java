package com.ry.base.server.protocol.tcp.message.ss;

public enum MessageSSID {
    Login_Request(100, "登录请求"),
    Login_Response(101, "登录响应"),
    Logout_Request(102, "登出请求"),
    Logout_Response(103, "登出响应"),
    Heartbeat_Request(104, "客户端心跳包"),
    Heartbeat_Response(105, "客户端心跳包应答");
    private int messageId;

    MessageSSID(int messageId, String messageDesc) {
        this.messageId = messageId;
    }

    public int getMessagegId() {
        return this.messageId;
    }

    public static MessageSSID fromMessageId(int messageId) {
        for (MessageSSID id : MessageSSID.values()) {
            if (id.getMessagegId() == messageId) {
                return id;
            }
        }
        return null;
    }
}
