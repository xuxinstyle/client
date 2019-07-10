package com.game.role.skill.packet;

import com.game.role.skill.model.SkillSlot;

import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 22:49
 */
public class SM_ShowSkillInfo {
    private long playerId;

    private Map<Integer, SkillSlot> skillSlotMap;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public Map<Integer, SkillSlot> getSkillSlotMap() {
        return skillSlotMap;
    }

    public void setSkillSlotMap(Map<Integer, SkillSlot> skillSlotMap) {
        this.skillSlotMap = skillSlotMap;
    }
}
