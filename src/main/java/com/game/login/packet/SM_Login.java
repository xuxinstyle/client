package com.game.login.packet;

/**
 * @Author：xuxin
 * @Date: 2019/5/18 15:55
 * @id 3
 */
public class SM_Login {
    //登录状态 1 成功， 0 失败
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
