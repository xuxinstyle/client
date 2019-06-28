package com.game.scence.service;

import com.game.scence.model.PlayerPosition;
import com.game.scence.packet.SM_Move;
import com.game.scence.packet.SM_ShowAccountInfo;
import com.game.scence.packet.bean.PlayerVO;
import com.socket.core.TSession;

import java.util.List;

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
    void enterMap(TSession session, String accountId,int sceneType,PlayerPosition position);

    /**
     * 查看当前地图的所有玩家
     * @param session
     * @param playerVOList
     */
    void showAllAccount(TSession session, List<PlayerVO> playerVOList);

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

    /**
     * 显示地图
     * @param session
     * @param playerPositionList
     */
    void showMap(TSession session, List<PlayerPosition> playerPositionList);

    /**
     * 玩家的操作
     * @param session
     * @param mapId
     */
    void doOperate(TSession session, int mapId);

    /**
     * 初始化场景信息
     */
    void init();
}
