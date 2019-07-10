package com.game.role.equip.packet;

/**
 * @Author：xuxin
 * @Date: 2019/6/16 18:45
 */
public class SM_Equip {
    /**
     * 穿戴状态 1 穿戴成功 2 穿戴失败
     */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
