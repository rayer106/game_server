package com.ry.centerserver;

import com.ry.base.server.starter.Server;
import com.ry.base.server.starter.TcpServer;
import com.ry.centerserver.handler.TcpInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CenterServerApplication {

    public static void main(String[] args) {
        //加载用户数据

        //监听接口，供其他服务注册进来

        //监听后台管理接口，供后台管理系统使用
//        Server server = new HttpServer();
//        MessageEvent.getInstance().register(1, server);
//        try {
//            server.port(12345)
//                    .initializer(new MessageReceiver())
//                    .start();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        Server server2 = new TcpServer();
        try {
            server2.port(12346)
                    .initializer(new TcpInitializer())
                    .start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}