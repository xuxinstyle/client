package com.game.role.skill.packet;

/**
 * @Author：xuxin
 * @Date: 2019/7/9 11:57
 */
public class SM_SetSkillBar {
    /**
     * 状态 1 成功 2 失败
     */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
