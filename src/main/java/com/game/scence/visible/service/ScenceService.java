package com.game.scence.visible.service;

import com.game.scence.visible.model.Position;
import com.game.scence.visible.packet.SM_Move;
import com.game.scence.visible.packet.SM_ShowAccountInfo;
import com.game.scence.visible.packet.bean.VisibleVO;
import com.socket.core.TSession;

import java.util.List;
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
     * @param mapId
     */
    void enterMap(TSession session, String accountId,int mapId, int sceneId);

    /**
     * 查看当前地图的所有玩家
     * @param session
     * @param visibleVOList
     */
    void showAllVisible(TSession session, List<VisibleVO> visibleVOList);

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
     * @param positionList
     */
    void showMap(TSession session, Map<Integer ,List<Position>> positionList);

    /**
     * 玩家的操作
     * @param session
     * @param
     */
    void doOperate(TSession session);

    /**
     * 初始化场景信息
     */
    void init();

    /**
     * 切换地图失败
     * @param session
     * @param preMapId
     * @param targetmapId
     */
    void changeMap(TSession session, int preMapId, int targetmapId);

    /**
     * 切换地图失败
     */
    void changeMapErr(TSession session,int status);

    /**
     * 进入地图失败
     */
    void enterMapErr();
}
