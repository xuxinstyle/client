package com.game.role.skill.packet;

import com.game.base.gameobject.ObjectType;
/**
 * 生物死亡
 * @Author：xuxin
 * @Date: 2019/7/10 21:54
 */
public class SM_CreatureDead {
    /**
     * mapId
     */
    private int mapId;
    /**
     * 类型
     */
    private ObjectType objectType;
    /**
     * 唯一标识id
     */
    private long objectId;
    /**
     * 生物名字
     */
    private String creatureUnitname;

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
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

    public String getCreatureUnitname() {
        return creatureUnitname;
    }

    public void setCreatureUnitname(String creatureUnitname) {
        this.creatureUnitname = creatureUnitname;
    }
}
