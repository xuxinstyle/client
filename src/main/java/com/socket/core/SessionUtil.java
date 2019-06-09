package com.socket.core;

import com.socket.dispatcher.action.IActionDispatcher;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class SessionUtil {
    private static final AttributeKey<TSession> SESSION_KEY = AttributeKey.valueOf("session-key");

    public static final boolean createChannelSession(Channel channel, IActionDispatcher actionDispatcher){
        Attribute<TSession> attr = channel.attr(SESSION_KEY);
        return attr.compareAndSet(null, new TSession(channel,actionDispatcher));
    }

    public static final TSession getChannelSession(Channel channel){
        Attribute<TSession> attr = channel.attr(SESSION_KEY);
        return attr.get();
    }

}