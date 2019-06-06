package com.game.scence.facade;

import com.game.SpringContext;
import com.game.scence.packet.*;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 14:15
 */
@Component
public class ScenceFacade {
    private static final Logger logger = LoggerFactory.getLogger(ScenceFacade.class);
    @HandlerAnno
    public void enterInitScence(TSession session, SM_EnterInitScence res){
        try {
            SpringContext.getScenceService().enterInitScence(session ,res.getAccountId(), res.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @HandlerAnno
    public void enterScence(TSession session, SM_EnterMap sm){
        try {
            SpringContext.getScenceService().enterMap(session,sm.getMapId());
        }catch (Exception e){
            logger.error("进入地图失败");
            e.printStackTrace();
        }
    }
    @HandlerAnno
    public void showAllAccount(TSession session, SM_ShowAllAccountInfo sm){
        try {
            SpringContext.getScenceService().showAllAccount(session, sm.getContext());
        }catch (Exception e){
            logger.error("查看{}地图所有玩家信息失败",session.getMapId());
            e.printStackTrace();
        }
    }

    @HandlerAnno
    public void showAccountInfo(TSession session, SM_ShowAccountInfo sm){
        try {
            SpringContext.getScenceService().showAccount(session, sm);
        }catch (Exception e){
            logger.error("查看{}地图玩家{}信息失败",session.getMapId(), sm.getAccountId());
            e.printStackTrace();
        }
    }
}
