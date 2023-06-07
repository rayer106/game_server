package com.ry.base.server.starter;

import com.ry.base.MessageEvent;
import com.ry.base.server.protocol.tcp.handler.TcpC2SInitializer;
import com.ry.base.common.IInitializer;

public class TcpServer extends Server {

    @Override
    public IInitializer getDefaultInitializer() {
        return new TcpC2SInitializer();
    }

    @Override
    public void start() throws Exception {
        super.start();
        MessageEvent.getInstance().post(1, 1);
    }
}
