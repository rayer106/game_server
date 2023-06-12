package com.ry.base.server.protocol.tcp.codec;

public class TcpPacketConfig {
    //------解码参数配置--------
    public static final int HEADER_LENGTH = 4;
    public static final int MAX_FRAME_LENGTH = Integer.MAX_VALUE;
    public static final int LENGTH_FIELD_OFFSET = 0;
    public static final int LENGTH_FIELD_LENGTH = 4;
    public static final int LENGTH_ADJUSTMENT = 0;
    public static final int INITIAL_BYTES_TO_STRIP = 4;
    //----------END-----------

    //------心跳参数配置--------
    public static final int IDLE_WRITE_TIME = 0;
    public static final int IDLE_READ_TIME = 0;
    public static final int IDLE_ALL_TIME = 15;
    //-----------END----------
}
