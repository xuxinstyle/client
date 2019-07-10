package com.game.scence.visible.facade;

import com.game.SpringContext;
import com.game.scence.visible.packet.*;
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
            SpringContext.getScenceService().enterMap(session,session.getAccountId(),sm.getMapId(),sm.getSceneId());
        }catch (Exception e){
            logger.error("进入地图失败");
            e.printStackTrace();
        }
    }
    @HandlerAnno
    public void showAllVisible(TSession session, SM_ShowAllVisibleInfo sm){
        try {
            SpringContext.getScenceService().showAllVisible(session, sm.getVisibleVOList());
        }catch (Exception e){
            logger.error("查看{}地图可视物信息失败",session.getMapId());
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
            System.out.println("移动到x={"+sm.getPosition().getX()+"},y={"+sm.getPosition().getY()+"}失败)");
            e.printStackTrace();
        }
    }

    @HandlerAnno
    public void showMap(TSession session, SM_ScenePositionVisible sm){
        try{
            SpringContext.getScenceService().showMap(session,sm.getPositionMap());
        }catch (Exception e){
            System.out.println("显示地图失败");
            e.printStackTrace();
        }
    }
    @HandlerAnno
    public void changeMap(TSession session, SM_ChangeMap sm){
        try{
            SpringContext.getScenceService().changeMap(session,sm.getPreMapId(),sm.getTargetmapId());
        }catch (Exception e){
            System.out.println("显示地图失败");
            e.printStackTrace();
        }
    }
    @HandlerAnno
    public void changeMapErr(TSession session, SM_ChangeMapErr sm){
        SpringContext.getScenceService().changeMapErr(session,sm.getStatus());
    }
    @HandlerAnno
    public void enterMapErr(TSession session, SM_EnterMapErr sm){
        SpringContext.getScenceService().enterMapErr();
    }
}
