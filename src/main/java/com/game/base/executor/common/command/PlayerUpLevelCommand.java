package com.game.base.executor.common.command;

import com.game.base.executor.common.Impl.AbstractCommonCommand;
import com.socket.core.TSession;
import com.socket.dispatcher.core.ActionDispatcher;

/**
 * @Author：xuxin
 * @Date: 2019/6/12 12:19
 */
public class PlayerUpLevelCommand extends AbstractCommonCommand {

    private TSession session;

    private int opIndex;

    private Object pack;

    public static PlayerUpLevelCommand valueOf(TSession session, int opIndex, Object pack ){
        PlayerUpLevelCommand command = new PlayerUpLevelCommand();
        command.setSession(session);
        command.setOpIndex(opIndex);
        command.setPack(pack);
        return command;
    }
    @Override
    public String getName() {
        return "PlayerUpLevelCommand";
    }

    @Override
    public void active() {
        ActionDispatcher.doHandle(session,opIndex,pack);
    }

    public TSession getSession() {
        return session;
    }

    public void setSession(TSession session) {
        this.session = session;
    }

    public int getOpIndex() {
        return opIndex;
    }

    public void setOpIndex(int opIndex) {
        this.opIndex = opIndex;
    }

    public Object getPack() {
        return pack;
    }

    public void setPack(Object pack) {
        this.pack = pack;
    }
}
