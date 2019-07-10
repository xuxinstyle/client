package com.game.scence.visible.packet;

/**
 * @Author：xuxin
 * @Date: 2019/6/11 21:15
 */
public class CM_ScenePositionVisible {

    private int mapId;
    public static CM_ScenePositionVisible valueOf(int mapId){
        CM_ScenePositionVisible cm = new CM_ScenePositionVisible();
        cm.setMapId(mapId);
        return cm;
    }
    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
}
