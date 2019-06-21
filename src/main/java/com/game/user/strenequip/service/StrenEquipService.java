package com.game.user.strenequip.service;

import com.socket.core.TSession;

/**
 * @Author：xuxin
 * @Date: 2019/6/20 10:23
 */
public interface StrenEquipService {
    /**
     * 响应服务端强化装备
     * @param session
     * @param status
     * @param itemName
     */
    void strenEquip(TSession session, int status, String itemName);
}
