package com.socket.core;

import com.game.connect.packet.CM_Connect;
import com.socket.dispatcher.core.ActionDispatcher;
import com.socket.heartbeat.HeartBeatPack;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 因为 Netty 采用线程池，所以这里使用原子操作类来进行计数
     */
    private static final int HEARTBEAT_INTERVAL = 30;
    Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    /**
     * 因为多线程，所以使用原子操作类来进行计数
     */
    private static final ActionDispatcher actionDispatcher = new ActionDispatcher();
    /**
     * 当客户端和服务端 TCP 链路建立成功之后，Netty 的 NIO 线程会调用 channelActive 方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if(!SessionUtil.createChannelSession(ctx.channel(), actionDispatcher)){
            ctx.channel().close();
            return;
        }
        SendConnectPack(ctx);
        /**
         * 定时发送心跳包
         *
         */
        scheduleSendHeartBeat(ctx);
    }
    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().scheduleAtFixedRate(()->{
            if(ctx.channel().isActive()){
                TSession session = SessionUtil.getChannelSession(ctx.channel());
                session.sendPacket(new HeartBeatPack());
            }
        },HEARTBEAT_INTERVAL,HEARTBEAT_INTERVAL,TimeUnit.SECONDS);
    }


    /**
     * 多余 数组、List、Set、Map 等，对立面的元素逐个进行发送，则对方也是逐个接收
     * 否则如果直接发送 数组、List、Set、Map 等，则对方会统一接收
     * 注意：因为使用LengthFieldPrepender、LengthFieldBasedFrameDecoder编解码器处理半包消息
     * 所以这里连续发送也不会出现 TCP 粘包/拆包
     *
     */
    private void SendConnectPack(ChannelHandlerContext ctx) {
        TSession session = SessionUtil.getChannelSession(ctx.channel());
        CM_Connect cm = new CM_Connect();
        session.sendPacket(cm);

    }


    /**
     * 当服务端返回应答消息时，channelRead 方法被调用，从 Netty 的 ByteBuf 中读取并打印应答消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //服务端消息传来的地方，在这个地方处理服务端传来的消息，并处理相关逻辑
        TSession session = SessionUtil.getChannelSession(ctx.channel());
        MyPack packet = (MyPack) msg;
        if(packet.getpId()==-1){
            return;
        }
        //分发处理
        actionDispatcher.handle(session,packet.getpId(),packet.getPacket(),System.nanoTime());
    }

    /**
     * 当发生异常时，打印异常 日志，释放客户端资源
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**释放资源*/
        //cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开连接");
        super.channelInactive(ctx);
        //Start.main(null);
    }
}
