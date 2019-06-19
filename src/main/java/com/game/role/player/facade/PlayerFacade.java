package com.game.role.player.facade;

import com.game.SpringContext;
import com.game.role.player.packet.SM_PlayerUpLevel;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/17 17:46
 */
@Component
public class PlayerFacade {
    @HandlerAnno
    public void playerUpLevel(TSession session, SM_PlayerUpLevel sm){
        try{
            SpringContext.getPlayerService().playerUpLevel(sm.getPlayerName(), sm.getLevel(),sm.getUpLevel() );
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("玩家升级失败");
        }
    }
}
