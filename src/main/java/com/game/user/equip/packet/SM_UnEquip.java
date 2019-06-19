package com.game.user.equip.packet;

/**
 * @Author：xuxin
 * @Date: 2019/6/16 18:45
 */
public class SM_UnEquip {
    /**
     * 装备卸下状态 1 成功 2 失败背包已满
     */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
