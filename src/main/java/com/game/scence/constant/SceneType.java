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

    private String typeName;

    private SceneType(int mapid, String typeName){
        this.mapid = mapid;
        this.typeName = typeName;
    }
    public static SceneType valueOf(int mapid){
        SceneType[] values = SceneType.values();
        for(SceneType sceneType : values){
            if(sceneType.getMapid()==mapid){
                return sceneType;
            }
        }
        return null;
    }
    public int getMapid() {
        return mapid;
    }

    public void setMapid(int mapid) {
        this.mapid = mapid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
