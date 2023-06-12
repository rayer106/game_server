package com.ry.base.server.protocol.tcp.handler;

import com.ry.base.common.IInitializer;
import com.ry.base.proto.impl.Message;
import com.ry.base.server.protocol.tcp.codec.TcpPacketConfig;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

public class TcpProtobufInitializer extends IInitializer {

    IdleHandler idleHandler = new IdleHandler();

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast("ProtobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder())
                .addLast("ProtobufDecoder", new ProtobufDecoder(Message.getDefaultInstance()))
                .addLast("ProtobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender())
                .addLast("ProtobufEncoder", new ProtobufEncoder())
                .addLast("idleStateHandler", new IdleStateHandler(TcpPacketConfig.IDLE_READ_TIME, TcpPacketConfig.IDLE_WRITE_TIME, TcpPacketConfig.IDLE_ALL_TIME))
                .addLast("idleHandler", idleHandler);
    }
}