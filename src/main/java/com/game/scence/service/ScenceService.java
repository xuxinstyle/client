package com.game.scence.service;

import com.game.scence.packet.SM_Move;
import com.game.scence.packet.SM_ShowAccountInfo;
import com.socket.core.TSession;

import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 16:40
 */
public interface ScenceService {
    /**
     * 进入地图
     * @param session
     * @param accountId
     * @param sceneType
     */
    void enterMap(TSession session, String accountId, String context, int sceneType,int x,int y);

    /**
     * 查看当前地图的所有玩家
     * @param session
     * @param context
     */
    void showAllAccount(TSession session, String context);

    /**
     * 查看指定玩家详细信息
     * @param session
     * @param sm
     */
    void showAccount(TSession session , SM_ShowAccountInfo sm);

    /**
     * 移动到指定位置
     * @param session
     * @param sm
     */
    void move(TSession session, SM_Move sm);
}
