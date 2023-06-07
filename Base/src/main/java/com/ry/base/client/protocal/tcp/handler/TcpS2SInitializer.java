package com.ry.base.client.protocal.tcp.handler;

import com.ry.base.client.protocal.tcp.codec.TcpS2SCodec;
import com.ry.base.common.IInitializer;
import com.ry.base.server.protocol.tcp.codec.TcpPacketConfig;
import com.ry.base.server.protocol.tcp.handler.IdleHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class TcpS2SInitializer extends IInitializer {
    IdleHandler idleHandler = new IdleHandler();

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(TcpPacketConfig.MAX_FRAME_LENGTH, TcpPacketConfig.LENGTH_FIELD_OFFSET, TcpPacketConfig.LENGTH_FIELD_LENGTH, TcpPacketConfig.LENGTH_ADJUSTMENT, TcpPacketConfig.INITIAL_BYTES_TO_STRIP))
                .addLast("codec", new TcpS2SCodec())
                .addLast("idleStateHandler", new IdleStateHandler(TcpPacketConfig.IDLE_READ_TIME, TcpPacketConfig.IDLE_WRITE_TIME, TcpPacketConfig.IDLE_ALL_TIME))
                .addLast("idleHandler", idleHandler);
    }
}