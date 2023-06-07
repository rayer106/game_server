package com.ry.base.server.protocol.tcp.message.cs;

public class TcpS2CEntity {

    private int length;
    private TcpS2CEntityHeader header;
    private String content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public TcpS2CEntityHeader getHeader() {
        return header;
    }

    public void setHeader(TcpS2CEntityHeader header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}