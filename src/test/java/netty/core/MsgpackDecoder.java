package netty.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import netty.Utils.ProtoStuffUtil;

import java.util.List;

/**
 * CMessagePack 解码器 - 继承 Netty 的 MessageToMessageDecoder,并重写方法
 */
public class MsgpackDecoder extends ReplayingDecoder {
    /**
     * 重写方法，首先从数据报 byteBuf 中获取需要解码的 byte 数组，
     * 然后调用 CMessagePack 的 read 方法将其反序列化为 Object 对象，将解码后的对象加入到解码列表 list 中，
     * 这样就完成了 CMessagePack 的解码操作
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list){


        try{
            System.out.println("解码前：");
            int length = byteBuf.readableBytes();
            System.out.println(length);
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array, 0, length);
            CMessagePack deserializer = ProtoStuffUtil.deserializer(array, CMessagePack.class);
            //System.out.println("解码后："+deserializer);
            //MessagePack messagePack = new MessagePack();
            list.add(deserializer);
            System.out.println("解码后："+deserializer);
        }catch (Exception e){
            System.out.println("解码异常");
            e.printStackTrace();
        }

    }
}
