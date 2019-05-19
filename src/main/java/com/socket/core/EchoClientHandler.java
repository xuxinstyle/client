package com.socket.core;

import com.game.connect.packet.CM_Connect;
import com.socket.Utils.ProtoStuffUtil;
import com.socket.dispatcher.config.RegistSerializerMessage;
import com.socket.dispatcher.core.ActionDispatcher;
import com.socket.dispatcher.executor.IdentifyThreadPoolExecutor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import org.msgpack.MessagePack;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 因为 Netty 采用线程池，所以这里使用原子操作类来进行计数
     */
    private static AtomicInteger atomicInteger = new AtomicInteger();
    Logger logger = Logger.getLogger(EchoClientHandler.class);
    /**
     * 因为多线程，所以使用原子操作类来进行计数
     */
    private static final ActionDispatcher actionDispatcher = new ActionDispatcher();
    /**
     * 当客户端和服务端 TCP 链路建立成功之后，Netty 的 NIO 线程会调用 channelActive 方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        /**
         * 多余 数组、List、Set、Map 等，对立面的元素逐个进行发送，则对方也是逐个接收
         * 否则如果直接发送 数组、List、Set、Map 等，则对方会统一接收
         * 注意：因为使用LengthFieldPrepender、LengthFieldBasedFrameDecoder编解码器处理半包消息
         * 所以这里连续发送也不会出现 TCP 粘包/拆包
         */
        MyPack myPack = new MyPack();
        myPack.setpId(2);
        CM_Connect cm = new CM_Connect();
        byte[] serializer = ProtoStuffUtil.serializer(cm);
        myPack.setPacket(serializer);
        myPack.setTime(System.nanoTime());
        ctx.writeAndFlush(myPack);
    }

    /**
     * 当服务端返回应答消息时，channelRead 方法被调用，从 Netty 的 ByteBuf 中读取并打印应答消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, java.lang.Object msg) throws Exception {
        //服务端消息传来的地方，在这个地方处理服务端传来的消息，并处理相关逻辑

        if(logger.isDebugEnabled()){
            logger.debug((atomicInteger.addAndGet(1)) + "--->" + Thread.currentThread().getName() + ",The server receive  order : " + msg);
        }else {
            atomicInteger.addAndGet(1);
        }
        List<Object> objects =(List<Object>)msg;
        if(objects.size()<=0){
            logger.error("错了错了，传来的包为空！");
            return;
        }
        String object0 = objects.get(0).toString();
        int opIndex = Integer.parseInt(object0);
        Object packet = objects.get(1);
        String time = objects.get(2).toString();
        TSession session = new TSession(ctx.channel(),actionDispatcher);
        Class<?> aClass = RegistSerializerMessage.idClassMap.get(opIndex);
        byte[] unpack = MessagePack.unpack(MessagePack.pack(packet), byte[].class);

        Object pack = ProtoStuffUtil.deserializer(unpack, aClass);
        //分发处理
        actionDispatcher.doHandle(session,opIndex,pack,Long.parseLong(time));
    }

    /**
     * 当发生异常时，打印异常 日志，释放客户端资源
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**释放资源*/
        cause.printStackTrace();
        ctx.close();
    }
}
