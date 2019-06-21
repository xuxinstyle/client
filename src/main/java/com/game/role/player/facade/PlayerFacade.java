package com.game.role.player.facade;

import com.game.SpringContext;
import com.game.role.player.packet.SM_PlayerUpLevel;
import com.game.role.player.packet.SM_ShowAttribute;
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
    public void playerUpLevel(TSession session, SM_PlayerUpLevel sm) {
        try {
            SpringContext.getPlayerService().playerUpLevel(sm.getStatus(), sm.getPlayerName(), sm.getLevel(), sm.getUpLevel());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("玩家升级失败");
        }
    }

    @HandlerAnno
    public void showAttribute(TSession session, SM_ShowAttribute sm) {
        try {
            SpringContext.getPlayerService().showAttribute(session, sm.getPlayerName(),
                    sm.getFirstAttributeList(), sm.getSecondAttributeList(), sm.getOtherAttributeList(),sm.getPlayerLevel());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查看角色[" + sm.getPlayerName() + "]属性失败");
        }
    }
}
