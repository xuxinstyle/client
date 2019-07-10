package com.game.role.equip.facade;

import com.game.SpringContext;
import com.game.role.equip.packet.SM_Equip;
import com.game.role.equip.packet.SM_ShowEquipInfo;
import com.game.role.equip.packet.SM_UnEquip;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/17 11:22
 */
@Component
public class EquipFacade {
    private static final Logger logger = LoggerFactory.getLogger(EquipFacade.class);
    @HandlerAnno
    public void equip(TSession session, SM_Equip sm){
        try{
            SpringContext.getEquipService().equip(session, sm.getStatus());
        }catch (Exception e){
            System.out.println("穿戴装备出错");
        }
    }

    @HandlerAnno
    public void unEquip(TSession session, SM_UnEquip sm){
        try{
            SpringContext.getEquipService().unEquip(session, sm.getStatus());
        }catch (Exception e){
            System.out.println("卸下装备出错");
        }
    }

    @HandlerAnno
    public void showEquipInfo(TSession session, SM_ShowEquipInfo sm){
        try{
            SpringContext.getEquipService().showEquipInfo(session, sm.getPlayerId(), sm.getPositionEquipment(), sm.getPlayerName(), sm.getJob());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查看装备出错");
        }
    }
}
