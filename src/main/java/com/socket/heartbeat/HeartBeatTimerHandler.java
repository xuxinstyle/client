package com.socket.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Author：xuxin
 * @Date: 2019/6/1 17:36
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
    private static final int HEARTBEAT_INTERVAL = 5;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        /**
         * 定时发送心跳包
         *
         */
        scheduleSendHeartBeat(ctx);

    }
    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(()->{
            if(ctx.channel().isActive()){

                HeartBeatRequestPack heartBeatRequestPack = new HeartBeatRequestPack();

                ctx.writeAndFlush(heartBeatRequestPack);
            }
        },HEARTBEAT_INTERVAL,TimeUnit.MINUTES);
    }
}
