package com.game.role.player.service;

import com.game.base.attribute.Attribute;
import com.socket.core.TSession;

import java.util.List;

/**
 * @Author：xuxin
 * @Date: 2019/6/17 17:50
 */
public interface PlayerService {
    void playerUpLevel(int status,String accountId, int proLevel, int afterLevel);

    /**
     * 显示玩家属性
     * @param session
     * @param playerName
     * @param firstAttribute
     * @param secondAttribute
     */
    void showAttribute(TSession session, String playerName, List<Attribute> firstAttribute, List<Attribute> secondAttribute);

}
