package com.game.role.skill.service;

import com.game.base.gameobject.ObjectType;
import com.game.role.skill.model.SkillSlot;
import com.socket.core.TSession;

import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 23:38
 */
public interface SkillService {
    /**
     * 查看技能信息
     * @param session
     * @param playerId
     * @param skillSlotMap
     */
    void showSkillInfo(TSession session, long playerId, Map<Integer, SkillSlot> skillSlotMap);

    /**
     * 查看快捷技能栏
     * @param session
     * @param playerId
     * @param skillBarMap
     */
    void showSkillBar(TSession session, long playerId, Map<Integer, Integer> skillBarMap);

    /**
     * 设置技能栏
     * @param session
     * @param status
     */
    void setSkillBar(TSession session, int status);

    /**
     * 使用技能
     * @param session
     * @param skillId
     */
    void useSkill(TSession session,int skillId);

    /**
     * 使用单体技能
     */
    void useSingleSkill(int skillBarId);

    /**
     * 使用aoe技能
     */
    void useAoeSkill(int skillBarId);

    /**
     * 使用技能响应
     * @param session
     */
    void useSkillToMonsterRes(TSession session,long unitId,String unitName,int skill, Map<Long, Long> hurtMap);

    /**
     * 使用技能错误提示
     * @param session
     * @param status
     */
    void useSkillToMonsterResErr(TSession session, int status);

    /**
     * 生物复活
     * @param objectType
     * @param objectId
     */
    void reviveCreature(ObjectType objectType, long objectId,String creatureUnitName);

    /**
     * 显示地图中谁死了
     * @param mapId
     * @param objectType
     * @param objectId
     * @param creatureUnitname
     */
    void creatureDead(int mapId, ObjectType objectType, long objectId, String creatureUnitname);
}
