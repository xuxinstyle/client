package com.netty.core;

import com.game.login.packet.CM_Login;
import org.msgpack.annotation.Message;

import java.util.Date;
import java.util.List;

@Message
public class MyPack {
    //协议Id
    private Integer pId;
    //自己的协议包
    private CM_Login cm;
    //发送时间
    private long time;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public CM_Login getCm() {
        return cm;
    }

    public void setCm(CM_Login cm) {
        this.cm = cm;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MyPack{" +
                "pId=" + pId +
                ", cm=" + cm +
                ", time=" + time +
                '}';
    }
}
