package com.game.login.service;

import com.socket.core.TSession;

/**
 * @Author：xuxin
 * @Date: 2019/5/29 16:28
 */
public interface ILoginService {
    /**
     * 客户端与服务端连接后客户端显示欢迎界面
     */
    void welcome(TSession session);
    /**
     * 登录后做的事情
     */
    void doLoginAfter(TSession session, int status, String accountId, int mapId, long playerId);

    /**
     * 登出
     */
    void logout(TSession session);
    /**
     * 账号输入错误
     */
    void loginNoAccount(TSession session);



}
