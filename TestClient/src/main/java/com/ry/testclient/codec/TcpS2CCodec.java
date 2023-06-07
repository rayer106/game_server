package com.ry.testclient.codec;

import com.ry.testclient.message.TcpS2CEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class TcpS2CCodec extends ByteToMessageCodec<TcpS2CEntity> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TcpS2CEntity o, ByteBuf byteBuf) {

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) {

    }
}
