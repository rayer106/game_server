package com.ry.base.server.protocol.tcp.message.ss;

public class TcpSSEntity {
    private int length;
    private TcpSSEntityHeader header;
    private byte[] body;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public TcpSSEntityHeader getHeader() {
        return header;
    }

    public void setHeader(TcpSSEntityHeader header) {
        this.header = header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
