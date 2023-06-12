package com.ry.base.server.protocol.tcp.handler;

import com.ry.base.LogUtil;
import com.ry.base.common.MsgSender;
import com.ry.base.proto.impl.Message;
import com.ry.base.proto.impl.MessageID;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@ChannelHandler.Sharable
public class IdleHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.ALL_IDLE) {
                //TODO 做一些事情
                LogUtil.i("userEventTriggered:" + state);
            }
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message msg) {
        //这里需要接收心跳包，并且要给客户端应答
        if (msg.getMsgHeader().getMsgId() == MessageID.HeartBeatReq) {
            LogUtil.i("IdleHandler.channelRead0, heart beat");
            //收到了心跳消息
            ctx.writeAndFlush(MsgSender.buildMessage(MessageID.HeartBeatResp));
        } else {
            LogUtil.i("IdleHandler.channelRead0, not heart beat");
            ctx.fireChannelRead(msg);
        }
    }
}