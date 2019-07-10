package com.game.role.strenequip.service;

import com.socket.core.TSession;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/20 10:24
 */
@Component
public class StrenEquipServiceImpl implements StrenEquipService {
    @Override
    public void strenEquip(TSession session, int status, String itemName) {
        if(status==1){
            System.out.println("道具["+itemName+"]强化次数+1");

        }else if(status == 2){
            System.out.println("强化失败，背包中没有该道具！");

        }else if(status == 3){
            System.out.println("强化失败，道具不足！");

        }else if(status == 4){
            System.out.println("道具["+itemName+"]无法强化！");

        }else if(status == 5){
            System.out.println("达到强化上限!");

        }else{
            System.out.println("非法状态");
        }

    }
}
