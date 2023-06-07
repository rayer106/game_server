package com.ry.base.client.connector;

import com.ry.base.client.protocal.tcp.handler.TcpS2SInitializer;
import com.ry.base.common.IInitializer;

public class TcpClient extends Client {
    @Override
    public IInitializer getDefaultInitializer() {
        return new TcpS2SInitializer();
    }
}