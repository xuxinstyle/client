package com.game.role.equipupgrade.service;


import com.socket.core.TSession;

/**
 * @Author：xuxin
 * @Date: 2019/6/20 14:50
 */
public interface EquipUpgradeService {
    /**
     * 装备升级品质
     * @param session
     */
    void equipUpgrade(TSession session, int status, String equipName);
}
