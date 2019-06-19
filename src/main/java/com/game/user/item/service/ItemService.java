package com.game.user.item.service;

import com.game.user.item.packet.bean.ItemVO;
import com.socket.core.TSession;

import java.util.List;

/**
 * @Author：xuxin
 * @Date: 2019/6/14 15:02
 */
public interface ItemService {
    /**
     * 查看玩家信息
     */
    void showItems(TSession session, List<ItemVO> list, int size, int useSize);

    /**
     * 发奖到背包中
     * @param session
     * @param status
     */
    void AwardToPack(TSession session, int status);
}
