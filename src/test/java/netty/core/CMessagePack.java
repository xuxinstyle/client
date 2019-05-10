package netty.core;

import java.io.Serializable;
import java.util.Arrays;

//连接服务器时客户端发生的协议

public class CMessagePack implements Serializable {
    //消息Id
    private int messageId;
    //消息长度
    //private int length;
    //消息数据
    private byte[] data;
    //发送时间
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CMessagePack{" +
                "messageId=" + messageId +
                ", data=" + Arrays.toString(data) +
                ", time=" + time +
                '}';
    }
}
