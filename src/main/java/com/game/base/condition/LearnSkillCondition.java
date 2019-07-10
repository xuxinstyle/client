package com.game.base.condition;


import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 21:12
 */
public class LearnSkillCondition{
    /**
     * 玩家等级
     */
    private int level;

    public static LearnSkillCondition valueOf(String conditionStr){
        LearnSkillCondition condition = new LearnSkillCondition();
        if(condition==null||"".equals(conditionStr)){
            return condition;
        }
        condition.setLevel(Integer.parseInt(conditionStr));
        return condition;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
