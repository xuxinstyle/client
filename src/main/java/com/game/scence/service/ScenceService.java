package com.game.scence.service;

import com.game.scence.packet.SM_ShowAccountInfo;
import com.socket.core.TSession;

import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 16:40
 */
public interface ScenceService {

    void enterInitScence(TSession session, String accountId, int sceneType);

    void enterMap(TSession session, int mapId);

    void showAllAccount(TSession session, String context);

    void showAccount(TSession session , SM_ShowAccountInfo sm);
}
