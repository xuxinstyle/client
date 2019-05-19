package com.game.connect.service;

import com.socket.core.TSession;

/**
 * @Author：xuxin
 * @Date: 2019/5/19 13:00
 */
public interface IConnectService {
    /**
     * 客户端与服务端连接后客户端想显示
     */
    void welcome(TSession session);
}
