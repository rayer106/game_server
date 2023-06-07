package com.ry.testclient;

import com.ry.testclient.handler.TestEncoderHandler;
import com.ry.testclient.handler.TestHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestClientApplication {

    public static void main(String[] args) {
        //SpringApplication.run(TestClientApplication.class, args);

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new TestEncoderHandler())
                                .addLast(new TestHandler());
                    }
                });
        try {
            ChannelFuture future = b.connect("192.168.1.188", 12346).sync();
            if (future.isSuccess()) {
                //Channel channel = future.channel();
                String id = future.channel().id().toString();
                //BootNettyClientChannel bootNettyClientChannel = new BootNettyClientChannel();
                //bootNettyClientChannel.setChannel(channel);
                //bootNettyClientChannel.setCode("clientId:"+id);
                //BootNettyClientChannelCache.save("clientId:"+id, bootNettyClientChannel);
                System.out.println("netty client start success=" + id);
                /**
                 * 等待连接端口关闭
                 */
                future.channel().closeFuture().sync();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
