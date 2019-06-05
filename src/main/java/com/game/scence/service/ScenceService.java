package com.game.scence.service;

import com.game.scence.constant.SceneType;
import com.game.scence.packet.SM_Move;
import com.socket.core.TSession;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 16:40
 */
public interface ScenceService {

    void enterInitScence(TSession session, String accountId, int sceneType);

    void enterMap(TSession session, int mapId);
}
