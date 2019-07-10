package com.game.base.account.service;

import com.socket.core.TSession;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 14:19
 */
public interface AccountService {
    /**
     * 进入创角页面
     * @param session
     * @param accountId
     */
    void enterCreatePlayer(TSession session, String accountId);

    /**
     *
     * @param session
     * @param accountId
     * @param status
     */
    void doCreatePlayerAfter(TSession session, String accountId, int status,long playerId);
}
