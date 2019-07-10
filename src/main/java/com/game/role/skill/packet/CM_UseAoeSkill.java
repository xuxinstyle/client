package com.game.role.skill.packet;

import com.game.scence.visible.model.Position;

/**
 * 使用aoe技能
 * @Author：xuxin
 * @Date: 2019/7/9 19:58
 */
public class CM_UseAoeSkill {
    /**
     * 位置
     */
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
