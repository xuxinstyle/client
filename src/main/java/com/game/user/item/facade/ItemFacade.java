package com.game.user.item.facade;

import com.game.SpringContext;
import com.game.user.item.packet.CM_ShowPackItem;
import com.game.user.item.packet.SM_AwardToPack;
import com.game.user.item.packet.SM_ShowPackItem;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/14 14:59
 */
@Component
public class ItemFacade {
    private static final Logger logger = LoggerFactory.getLogger(ItemFacade.class);
    @HandlerAnno
    public void showPack(TSession session, SM_ShowPackItem sm){
        try{
            SpringContext.getItemService().showItems(session,sm.getItemList(), sm.getSize(),sm.getUseSize());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查看玩家{}信息失败", session.getAccountId());
        }
    }
    @HandlerAnno
    public void showPack(TSession session, SM_AwardToPack sm){
        try{
            SpringContext.getItemService().AwardToPack(session,sm.getStatus());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查看玩家{}信息失败", session.getAccountId());
        }
    }
}
