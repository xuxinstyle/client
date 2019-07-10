package com.game.role.strenequip.facade;

import com.game.SpringContext;
import com.game.role.strenequip.packet.SM_StrenEquip;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/20 9:07
 */
@Component
public class StrenEquipFacade {
    @HandlerAnno
    public void stenEquip(TSession session, SM_StrenEquip sm){
        SpringContext.getStrenEquipService().strenEquip(session,sm.getStatus(),sm.getItemName());
    }
}
