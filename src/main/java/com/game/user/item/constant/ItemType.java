package com.game.user.item.constant;

/**
 * @Author：xuxin
 * @Date: 2019/6/12 18:27
 */
public enum  ItemType {

    /** 装备*/
    EQUIPMENT(1),
    /** 经验*/
    EXP(2),
    /** 可消耗的石头*/
    CONSUME_STONE(3),
    /** 药品*/
    MEDICINE(4),

    ;
    /** 道具类型Id*/
    private int id;

    ItemType(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
