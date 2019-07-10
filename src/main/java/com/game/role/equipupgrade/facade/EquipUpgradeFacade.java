package com.game.role.equipupgrade.facade;

import com.game.SpringContext;
import com.game.role.equipupgrade.packet.SM_EquipUpgrade;

import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/20 14:45
 */
@Component
public class EquipUpgradeFacade {
    private static final Logger logger = LoggerFactory.getLogger(EquipUpgradeFacade.class);
    @HandlerAnno
    public void equipUpgrade(TSession session, SM_EquipUpgrade sm){
        try {
            SpringContext.getEquipUpgradeService().equipUpgrade(session,sm.getStatus(),sm.getEquipName());
        }catch (Exception e){
            logger.error("玩家{}装备{}升级失败",session.getAccountId(),sm.getEquipName());
            e.printStackTrace();
        }
    }
}
