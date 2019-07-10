package com.game.role.equip.packet;

/**
 * 穿装备
 * @Author：xuxin
 * @Date: 2019/6/16 11:12
 */
public class CM_Equip {

    private String accountId;
    /**
     * 道具唯一id
     */
    private long itemObjectId;

    /**
     * 装备位置
     *
     */
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public long getItemObjectId() {
        return itemObjectId;
    }

    public void setItemObjectId(long itemObjectId) {
        this.itemObjectId = itemObjectId;
    }
}
