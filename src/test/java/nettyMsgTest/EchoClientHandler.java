package nettyMsgTest;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

        /**
         * 多余 数组、List、Set、Map 等，对立面的元素逐个进行发送，则对方也是逐个接收
         * 否则如果直接发送 数组、List、Set、Map 等，则对方会统一接收
         * 注意：因为使用LengthFieldPrepender、LengthFieldBasedFrameDecoder编解码器处理半包消息
         * 所以这里连续发送也不会出现 TCP 粘包/拆包
         */
        List<User> users = getUserArrayData();
        for (User user : users) {
            ctx.writeAndFlush(user);
        }
        ctx.writeAndFlush("我是普通的字符串消息" + Thread.currentThread().getName());
    }

    /**
     * 当服务端返回应答消息时，channelRead 方法被调用，从 Netty 的 ByteBuf 中读取并打印应答消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //服务端消息传来的地方，在这个地方处理服务端传来的消息，并处理相关逻辑
        System.out.println((atomicInteger.addAndGet(1)) + "---" + Thread.currentThread().getName() + ",Server return Message：" + msg);
    }

    /**
     * 当发生异常时，打印异常 日志，释放客户端资源
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**释放资源*/
        ctx.close();
    }

    /**
     * 设置网络传输的 POJO 对象数组/列表
     *
     * @return
     */
    public List<User> getUserArrayData() {
        User[] users = new User[5];
        User loopUser = null;
        for (int i = 0; i < 5; i++) {
            loopUser = new User();
            loopUser.setpId(i + 1);
            loopUser.setpName("华安" + Thread.currentThread().getName());
            loopUser.setIsMarry(true);
            loopUser.setBirthday(new Date());
            users[i] = loopUser;
        }
        return Arrays.asList(users);
    }
}
