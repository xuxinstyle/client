package com.game.user.item.facade;

import com.game.SpringContext;
import com.game.user.item.packet.*;
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
            System.out.println("查看背包失败");
        }
    }
    @HandlerAnno
    public void awardToPack(TSession session, SM_AwardToPack sm){
        try{
            SpringContext.getItemService().AwardToPack(session,sm.getStatus());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发奖失败");
        }
    }
    @HandlerAnno
    public void useItem(TSession session, SM_UseItem sm){
        try {
            SpringContext.getItemService().useItem(session, sm.getStatus(),sm.getEffectiveTime());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("道具使用失败");
        }
    }

    @HandlerAnno
    public void effectEnd(TSession session, SM_EffectEnd sm){
        try{
            SpringContext.getItemService().effectEnd(sm.getItemName());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("道具失效错误");
        }
    }

    @HandlerAnno
    public void showItemInfo(TSession session, SM_ShowItemInfo sm){
        try{
            SpringContext.getItemService().showItemInfo(session,sm);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查看道具信息失败");
        }
    }
}
