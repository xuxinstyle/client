package com.game.login.packet;

import org.msgpack.annotation.Message;

/**
 * 登录协议
 */
@Message
public class CM_Login {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String passward;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }
}
