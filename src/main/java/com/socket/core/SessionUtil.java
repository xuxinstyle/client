package com.socket.core;

import com.socket.dispatcher.action.IActionDispatcher;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class SessionUtil {
    private static final AttributeKey<TSession> SESSION_KEY = AttributeKey.valueOf("session-key");

    private static Channel channel ;
    private static TSession session;

    public static final boolean createChannelSession(Channel channel, IActionDispatcher actionDispatcher){
        Attribute<TSession> attr = channel.attr(SESSION_KEY);
        setChannel(channel);
        setSession(new TSession(channel,actionDispatcher));
        return attr.compareAndSet(null,getSession());
    }

    public static final TSession getChannelSession(Channel channel){
        Attribute<TSession> attr = channel.attr(SESSION_KEY);
        return attr.get();
    }

    public static TSession getSession() {
        return session;
    }

    public static void setSession(TSession session) {
        SessionUtil.session = session;
    }

    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        SessionUtil.channel = channel;
    }
}