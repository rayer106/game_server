package com.ry.base.server.protocol.tcp.codec;

import com.ry.base.server.protocol.tcp.message.cs.TcpC2SEntity;
import com.ry.base.server.protocol.tcp.message.cs.TcpC2SEntityHeader;
import com.ry.base.server.protocol.tcp.message.cs.TcpS2CEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.ry.base.LogUtil.i;

public class TcpC2SCodec extends ByteToMessageCodec<TcpS2CEntity> {
    //===================================================================
    //|消息体长度|消息ID|消息类型|数据内容|
    //|4字节    |4字节 |4字节  |
    //===================================================================
    @Override
    protected void encode(ChannelHandlerContext cxt, TcpS2CEntity tcpEntity, ByteBuf out) throws Exception {

        //ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        byte[] content = tcpEntity.getContent().getBytes(StandardCharsets.UTF_8);
        int length = 12 + content.length;
        out.writeInt(length);
        out.writeInt(tcpEntity.getHeader().getMessageId());
        out.writeInt(tcpEntity.getHeader().getMessageType());
        out.writeInt(content.length);
        out.writeBytes(content);

        cxt.writeAndFlush(out);
    }

    //===================================================================
    //|消息体长度|消息ID|消息类型|token长度|token|数据内容|
    //|4字节    |4字节 |4字节  |4字节    |
    //===================================================================
    @Override
    protected void decode(ChannelHandlerContext cxt, ByteBuf in, List<Object> out) throws Exception {
        i("TCPRequestDecoder:decode   1");
//        if (in.readableBytes() < TcpPacketConfig.HEADER_LENGTH) {
//            i("TCPRequestDecoder:decode   4");
//            return;
//        }
//
//        i("TCPRequestDecoder:decode   2");
//        //标记一下当前读取数据的索引，如果下面不能继续则需要重置回当前
//        in.markReaderIndex();
//
//        //这里读取了data的长度，如果可读写字段不足，则等待下一次调用，如果字节数够了，则重新从头读取
//        int messageLength = in.readInt();
//        if (in.readableBytes() < messageLength) {
//            i("TCPRequestDecoder:decode   5");
//            //剩余可读取的字节数不够组成content，则重置读取索引
//            in.resetReaderIndex();
//            return;
//        }

        //TODO 读取Header的值
        TcpC2SEntity tcpEntity = new TcpC2SEntity();
        //tcpEntity.setLength(messageLength);
        TcpC2SEntityHeader header = new TcpC2SEntityHeader();
        header.setMessageId(in.readInt());
        header.setMessageType(in.readInt());
        int tokenLength = in.readInt();
        header.setToken(in.readBytes(tokenLength).toString(StandardCharsets.UTF_8));
        tcpEntity.setHeader(header);

        //TODO 验证TOKEN,如果TOKEN非法则关闭客户端连接，返回错误
        //i("TCPRequestDecoder:decode   3");
        //int dataLength = messageLength - 12 - tokenLength;
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        tcpEntity.setContent(data);

        String dataStr = new String(data, StandardCharsets.UTF_8);
        i("TCPRequestDecoder:decode   2," + dataStr);

        //TODO 需要一个序列化和反序列化的框架，如：Protobuf

        //TODO 解析完成，把完整的消息交给下一个handler处理
        out.add(tcpEntity);
        //cxt.fireChannelRead(tcpEntity);
    }
}
