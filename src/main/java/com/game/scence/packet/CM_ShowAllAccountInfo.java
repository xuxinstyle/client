package com.game.scence.packet;

/**
 * 查看当前场景的所有玩家的信息
 * @Author：xuxin
 * @Date: 2019/6/6 10:02
 */
public class CM_ShowAllAccountInfo {
    /**
     * 场景id
      */
    private int mapId;

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
}

