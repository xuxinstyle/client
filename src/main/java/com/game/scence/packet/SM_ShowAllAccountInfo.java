package com.game.scence.packet;

import java.util.Map;

/**
 * 响应查看场景中的玩家
 * @Author：xuxin
 * @Date: 2019/6/6 10:02
 */
public class SM_ShowAllAccountInfo {
    /**
     * <账号Id，昵称>
     */
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}