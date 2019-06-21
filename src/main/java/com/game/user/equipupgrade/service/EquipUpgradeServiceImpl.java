package com.game.user.equipupgrade.service;

import com.game.SpringContext;
import com.socket.core.TSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/20 14:50
 */
@Component
public class EquipUpgradeServiceImpl implements EquipUpgradeService {
    private static final Logger logger = LoggerFactory.getLogger(EquipUpgradeServiceImpl.class);

    @Override
    public void equipUpgrade(TSession session, int status, String equipName) {
        if (status == 1) {
            System.out.println("装备升级成功！");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        } else if (status == 2) {
            System.out.println("背包中没有道具");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        } else if (status == 3) {
            System.out.println("道具无法升级");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        } else if (status == 4) {
            System.out.println("道具不足，无法升级");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        } else if (status == 5) {
            System.out.println("装备达到等级上限，无法升级！");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }
    }
}
