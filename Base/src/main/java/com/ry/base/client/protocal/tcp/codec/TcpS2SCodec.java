package com.ry.base.client.protocal.tcp.codec;

import com.ry.base.server.protocol.tcp.codec.TcpPacketConfig;
import com.ry.base.server.protocol.tcp.message.cs.TcpC2SEntity;
import com.ry.base.server.protocol.tcp.message.cs.TcpS2CEntity;
import com.ry.base.server.protocol.tcp.message.cs.TcpS2CEntityHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.ry.base.LogUtil.i;

public class TcpS2SCodec extends ByteToMessageCodec<TcpC2SEntity> {
    //===================================================================
    //|消息体长度|消息ID|消息类型|数据内容|
    //|4字节    |4字节 |4字节  |
    //===================================================================
    @Override
    protected void encode(ChannelHandlerContext cxt, TcpC2SEntity tcpEntity, ByteBuf byteBuf) throws Exception {

        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        byte[] content = tcpEntity.getContent();
        int length = 8 + content.length;
        buf.writeInt(length);
        buf.writeInt(tcpEntity.getHeader().getMessageId());
        buf.writeInt(tcpEntity.getHeader().getMessageType());
        buf.writeBytes(content);

        cxt.writeAndFlush(buf);
    }

    //===================================================================
    //|消息体长度|消息ID|消息类型|token长度|token|数据内容|
    //|4字节    |4字节 |4字节  |4字节    |
    //===================================================================
    @Override
    protected void decode(ChannelHandlerContext cxt, ByteBuf in, List<Object> list) throws Exception {
        i("TCPRequestDecoder:decode   1");
        if (in.readableBytes() < TcpPacketConfig.HEADER_LENGTH) {
            return;
        }

        i("TCPRequestDecoder:decode   2");
        //标记一下当前读取数据的索引，如果下面不能继续则需要重置回当前
        in.markReaderIndex();

        int messageLength = in.readInt();
        if (in.readableBytes() < messageLength) {
            //剩余可读取的字节数不够组成content，则重置读取索引
            in.resetReaderIndex();
            return;
        }

        in.resetReaderIndex();

        //TODO 读取Header的值
        TcpS2CEntity tcpEntity = new TcpS2CEntity();
        tcpEntity.setLength(in.readInt());
        TcpS2CEntityHeader header = new TcpS2CEntityHeader();
        header.setMessageId(in.readInt());
        header.setMessageType(in.readInt());
        tcpEntity.setHeader(header);

        //TODO 验证TOKEN,如果TOKEN非法则关闭客户端连接，返回错误
        i("TCPRequestDecoder:decode   3");
        int dataLength = tcpEntity.getLength() - 8;
        if (dataLength > 0) {//后面还有数据
            byte[] data = new byte[dataLength];
            in.readBytes(data);

            //tcpEntity.setContent(data);

            String dataStr = new String(data, StandardCharsets.UTF_8);
            i("TCPRequestDecoder:decode   4," + dataStr);
        }
        //TODO 需要一个序列化和反序列化的框架，如：Protobuf

        //TODO 解析完成，把完整的消息交给下一个handler处理
        cxt.fireChannelRead(tcpEntity);
    }
}
