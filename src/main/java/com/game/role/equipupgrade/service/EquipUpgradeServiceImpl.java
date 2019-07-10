package com.game.role.equipupgrade.service;

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

        } else if (status == 2) {
            System.out.println("背包中没有道具");

        } else if (status == 3) {
            System.out.println("道具无法升级");

        } else if (status == 4) {
            System.out.println("升级条件不足，无法升级");

        } else if (status == 5) {
            System.out.println("装备达到等级上限，无法升级！");
        }
    }
}
