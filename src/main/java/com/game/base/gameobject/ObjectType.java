package com.game.base.gameobject;

/**
 * 用来定义游戏世界中所有的对象类型
 * @Author：xuxin
 * @Date: 2019/6/3 11:27
 */
public enum ObjectType {
    /**
     * 玩家角色
     */
    PLAYER(1,"玩家角色"),
    /**
     * NPC
     */
    NPC(2,"NPC"),
    /**
     * 怪物
     */
    MONSTER(3,"怪物"),
    /**
     * 道具
     */
    ITEM(4,"道具"),
    ;

    private int typeId;
    /**
     * Object名
     */
    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    ObjectType(int objectId, String objectName){
        this.typeId = objectId;
        this.objectName = objectName;
    }

    public int getTypeId() {
        return typeId;
    }

}
