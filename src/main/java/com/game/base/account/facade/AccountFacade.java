package com.game.base.account.facade;

import com.game.SpringContext;
import com.game.base.account.packet.SM_CreatePlayer;
import com.game.base.account.packet.SM_EnterCreatePlayer;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 14:16
 */
@Component
public class AccountFacade {
    private static final Logger logger = LoggerFactory.getLogger(AccountFacade.class);

    /**
     * FIXME:这里的session需要加accountId字段
     * @param session
     * @param sm
     */
    @HandlerAnno
    public void enterCreatePlayer(TSession session, SM_EnterCreatePlayer sm){
        try {
            SpringContext.getAccountService().enterCreatePlayer(session, sm.getAccountId());
        }catch (Exception e){
            logger.error("进入创建角色页面失败"+e.toString());
        }
    }
    @HandlerAnno
    public void createPlayerAfter(TSession session, SM_CreatePlayer res){
        try {
            SpringContext.getAccountService().doCreatePlayerAfter(session, res.getAccountId(), res.getStatus(),res.getPlayerId());
        }catch (Exception e){
            logger.error("进入创建角色页面失败"+e.toString());
        }
    }
}
