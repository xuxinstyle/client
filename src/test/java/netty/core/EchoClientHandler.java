package netty.core;

import game.connection.packet.TestBean;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.Utils.ProtoStuffUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 因为 Netty 采用线程池，所以这里使用原子操作类来进行计数
     */
    private static AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 当客户端和服务端 TCP 链路建立成功之后，Netty 的 NIO 线程会调用 channelActive 方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try{
            /**
             * 多余 数组、List、Set、Map 等，对立面的元素逐个进行发送，则对方也是逐个接收
             * 否则如果直接发送 数组、List、Set、Map 等，则对方会统一接收
             * 注意：因为使用LengthFieldPrepender、LengthFieldBasedFrameDecoder编解码器处理半包消息
             * 所以这里连续发送也不会出现 TCP 粘包/拆包
             */
            CMessagePack cm= new CMessagePack();
            cm.setMessageId(1);
            TestBean tb = new TestBean();
            tb.setCode(1);
            tb.setStr("规范格式");
            byte[] btmsg = ProtoStuffUtil.serializer(tb);
            cm.setData(btmsg);
            cm.setTime(System.nanoTime());
            //cm.setData("haha".getBytes());
            ctx.writeAndFlush(cm);
        }catch (Exception e){
            System.out.println("客户端ChannelActive异常");
            e.printStackTrace();
        }
    }

    /**
     * 当服务端返回应答消息时，channelRead 方法被调用，从 Netty 的 ByteBuf 中读取并打印应答消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try{
            //服务端消息传来的地方，在这个地方处理服务端传来的消息，并处理相关逻辑
            System.out.println("接收服务端消息");
            System.out.println((atomicInteger.addAndGet(1)) + "---" + Thread.currentThread().getName() + ",Server return Message：" + msg);
        }catch (Exception e){
            System.out.println("接收服务端信息异常");
            e.printStackTrace();
        }

    }

    /**
     * 当发生异常时，打印异常 日志，释放客户端资源
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        try{
            /*cause.printStackTrace();*/
            /**释放资源*/
           /* ctx.close();*/
        }catch (Exception e){
            System.out.println("客户端释放资源失败");
            e.printStackTrace();
        }
    }

}
