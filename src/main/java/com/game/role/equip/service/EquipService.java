package com.game.role.equip.service;

import com.game.role.equip.packet.bean.EquipmentVO;
import com.socket.core.TSession;

import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/6/17 11:24
 */
public interface EquipService {
    /**
     * 穿装备响应
     * @param session
     * @param status
     */
    void equip(TSession session, int status);

    /**
     * 卸装备响应
     * @param session
     * @param status
     */
    void unEquip(TSession session,int status);

    /**
     * 查看玩家装备栏信息
     * @param session
     * @param playerId
     * @param positionEquipment
     */
    void showEquipInfo(TSession session, long playerId, Map<Integer, EquipmentVO> positionEquipment, String playerName, int job);
}
