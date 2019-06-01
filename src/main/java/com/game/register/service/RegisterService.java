package com.game.register.service;

import com.socket.core.TSession;

/**
 * @Author：xuxin
 * @Date: 2019/5/29 16:37
 */
public interface RegisterService {
    /**
     * 玩家注册账号
     */
    void register(TSession session);
}
