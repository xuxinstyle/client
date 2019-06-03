package com.game.scence.constant;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 11:24
 */
public enum SceneType {
    /**
     * 新手村
     */
    NoviceVillage(1, "新手村"),
    /**
     * 野外
     */
    FIELD(2, "野外");

    private int mapid;

    private String name;
    private SceneType(int mapid, String name){
        this.mapid = mapid;
    }

    public int getMapid() {
        return mapid;
    }

    public void setMapid(int mapid) {
        this.mapid = mapid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
