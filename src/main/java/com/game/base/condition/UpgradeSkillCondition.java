package com.game.base.condition;


import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 20:11
 */
public class UpgradeSkillCondition {
    /**
     * 角色等级
     */
    private int level;

    public static UpgradeSkillCondition valueOf(int level){
        UpgradeSkillCondition condition = new UpgradeSkillCondition();
        condition.setLevel(level);
        return condition;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
