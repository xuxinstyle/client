package com.game.role.skill.constant;

import com.game.SpringContext;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 12:23
 */
public enum SkillType {
    /**
     * 单体技能
     */
    SINGLE_SKILL(1){
        @Override
        public void useSkill(int skillBarId){
            SpringContext.getSkillService().useSingleSkill(skillBarId);
        }
    },

    /**
     * 多目标技能
     */
    GROUP_SKILL(2){

    },
    /**
     * AOE技能
     */
    AOE_SKILL(3),
    /**
     * 属性buff技能
     */
    ATTRIBUTE_SKILL(4){

    },
    ;
    int id;

    SkillType(int id){
        this.id = id;
    }

    public void useSkill(int skillBarId){
        SpringContext.getSkillService().useSingleSkill(skillBarId);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
