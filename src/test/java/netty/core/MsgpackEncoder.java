package netty.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.Utils.ProtoStuffUtil;

/**
 * CMessagePack 编码器 —— 继承 Netty 的 MessageToByteEncoder，比重写方法
 */
public class MsgpackEncoder extends MessageToByteEncoder<CMessagePack> {

    /**
     * 重写方法，负责将 CM_Connect 类型的 POJO 对象编码为 byte 数组，然后写入 ByteBuf 中
     * @param channelHandlerContext
     * @param o
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CMessagePack o, ByteBuf byteBuf) {
        try{
            System.out.println("编码前:");
            byte[] serializer = ProtoStuffUtil.serializer(o);
            /** 序列化对象*/
            // byte[] raw = messagePack.write(o);
            int length = serializer.length;
            System.out.println("byteBuf长度："+length);
            byteBuf.writeBytes(serializer);
            System.out.println("编码后：");
        }catch (Exception e){
            System.out.println("编码异常");
            e.printStackTrace();
        }

    }
}
