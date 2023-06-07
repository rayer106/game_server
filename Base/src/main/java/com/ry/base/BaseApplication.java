package com.ry.base;

import io.netty.bootstrap.ServerBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        //SpringApplication.run(BaseApplication.class, args);
        System.out.println("BaseApplication is running");
    }

}
