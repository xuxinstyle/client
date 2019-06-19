package com.game.base.account.service;

import com.game.SpringContext;
import com.game.base.account.packet.CM_CreatePlayer;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 14:19
 */
@Component
public class AccountServiceImpl implements AccountService {

    @Override
    public void enterCreatePlayer(TSession session, String accountId) {
        Scanner scanner = new Scanner(System.in);
        CM_CreatePlayer cm  = new CM_CreatePlayer();
        cm.setAccountId(accountId);

        System.out.println("请给你的角色取个吊炸天的昵称吧：");
        String nickName = scanner.next();
        cm.setNickName(nickName);
        String career = "";
        while(true) {
            System.out.println("请选择你要创建的职业：选择三种职业中的一种（战士:1 /法师:2 /刺客:3）");
            career=scanner.next();
            if (Integer.parseInt(career) > 3 || Integer.parseInt(career) < 1) {
                System.out.println("非法输入，请重新输入！");
                continue;
            }
            break;
        }
        cm.setCareer(Integer.parseInt(career));
        session.sendPacket(cm);

    }

    @Override
    public void doCreatePlayerAfter(TSession session, String accountId, int status) {
        if(status==1){
            System.out.println(accountId+" 恭喜你创建角色成功");


        }else if(status == 0){
            SpringContext.getAccountService().enterCreatePlayer(session,accountId);
        }
    }
}
