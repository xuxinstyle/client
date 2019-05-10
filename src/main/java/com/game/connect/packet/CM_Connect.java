package com.game.connect.packet;

import org.msgpack.annotation.Message;

@Message
public class CM_Connect {
    //消息Id
    private final int msgId=1;

    private String context;

    public int getMsgId() {
        return msgId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
