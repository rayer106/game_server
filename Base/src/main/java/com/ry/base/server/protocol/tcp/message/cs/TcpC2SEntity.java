package com.ry.base.server.protocol.tcp.message.cs;

public class TcpC2SEntity {

    private int length;
    private TcpC2SEntityHeader header;
    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public TcpC2SEntityHeader getHeader() {
        return header;
    }

    public void setHeader(TcpC2SEntityHeader header) {
        this.header = header;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
