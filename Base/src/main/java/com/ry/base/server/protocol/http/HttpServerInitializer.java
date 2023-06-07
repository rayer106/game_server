package com.ry.base.server.protocol.http;

import com.ry.base.common.IInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends IInitializer {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("codec", new HttpServerCodec())
                .addLast("compressor", new HttpContentCompressor())
                .addLast("aggregator", new HttpObjectAggregator(65535));

//        List<Class<? extends ChannelInboundHandler>> inboundHanders = getHandlers();
//        if (inboundHanders != null) {
//            for (Class<? extends ChannelInboundHandler> cls : inboundHanders) {
//                try {
//                    ChannelInboundHandler handler = cls.newInstance();
//                    pipeline.addLast(handler.toString(), handler);
//                } catch (InstantiationException e) {
//                    throw new RuntimeException(e);
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
    }
}
