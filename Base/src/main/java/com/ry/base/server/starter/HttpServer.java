package com.ry.base.server.starter;

import com.ry.base.server.protocol.http.HttpServerInitializer;
import com.ry.base.common.IInitializer;

public class HttpServer extends Server {

    @Override
    public IInitializer getDefaultInitializer() {
        return new HttpServerInitializer();
    }
}
