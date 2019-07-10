package com.game.scence.visible.packet;

/**
 * @Author：xuxin
 * @Date: 2019/6/5 17:06
 */
public class SM_EnterMap {

    /** mapId*/
    private int mapId;
    /**
     * 场景id
     */
    private int sceneId;
    /**
     * 账号Id
     */
    private String accountId;


    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }



    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
}
