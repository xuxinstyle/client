package com.game.user.item.packet;

/**
 * 使用道具状态
 * @Author：xuxin
 * @Date: 2019/6/21 9:19
 */
public class SM_UseItem {
    /**
     * 使用状态
     */
    private int status;
    /**
     * 有效时长
     */
    private long effectiveTime;

    public long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
