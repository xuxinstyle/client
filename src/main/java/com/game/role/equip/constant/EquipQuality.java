package com.game.role.equip.constant;

/**
 * @Author：xuxin
 * @Date: 2019/6/18 19:42
 */
public enum EquipQuality {
    ORDINARY(1,"普通"),
    RARE(2,"稀有"),
    EPIC(3,"史诗"),
    LEGEND(4,"传说"),
    MYTH(5,"神话"),
    ;
    EquipQuality(int quality, String qualityName){
        this.quality = quality;
        this.qualityName = qualityName;
    }
    public static EquipQuality valueOf(int quality){
        for(EquipQuality equipQuality:EquipQuality.values()){
            if(equipQuality.getQuality()==quality){
                return equipQuality;
            }
        }
        return null;
    }
    /**
     * 品质
     */
    private int quality;
    /**
     * 名称
     */
    private String qualityName;

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getQualityName() {
        return qualityName;
    }

    public void setQualityName(String qualityName) {
        this.qualityName = qualityName;
    }
}
