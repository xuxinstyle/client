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
    public void enterScence(TSession session, SM_EnterMap sm){
        try {
            SpringContext.getScenceService().enterMap(session,session.getAccountId(),sm.getContext(),sm.getPosition(),sm.getMapId(),sm.getX(),sm.getY());
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
    @HandlerAnno
    public void move(TSession session, SM_Move sm){
        try{
            SpringContext.getScenceService().move(session,sm);
        }catch (Exception e){
            System.out.println("移动到x={"+sm.getX()+"},y={"+sm.getY()+"}失败); sm.getX(),sm.getY()");
            e.printStackTrace();
        }
    }

    @HandlerAnno
    public void showMap(TSession session, SM_OnlinePlayerOperate sm){
        try{
            SpringContext.getScenceService().showMap(session,sm.getScenePositions());
        }catch (Exception e){
            System.out.println("显示地图失败");
            e.printStackTrace();
        }
    }
}
