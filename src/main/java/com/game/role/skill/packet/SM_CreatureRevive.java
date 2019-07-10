package com.game.role.skill.packet;

import com.game.base.gameobject.ObjectType;

/**
 * @Author：xuxin
 * @Date: 2019/7/10 20:09
 */
public class SM_CreatureRevive {
    /**
     * 复活单元类型
     */
    private ObjectType objectType;
    /**
     * 唯一id
     */
    private long objectId;
    /**
     * 生物名称
     */
    private String creatureName;

    public static SM_CreatureRevive valueOf(ObjectType objectType, long objectId){
        SM_CreatureRevive sm = new SM_CreatureRevive();
        sm.setObjectId(objectId);
        sm.setObjectType(objectType);
        return sm;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public void setCreatureName(String creatureName) {
        this.creatureName = creatureName;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }
}
