package com.socket.dispatcher.executor;

/**
 * @Author：xuxin
 * @Date: 2019/4/29 14:57
 */
public interface IIdentifyThreadPool {
    void addSessionTask(TSession session, Runnable task);
}
