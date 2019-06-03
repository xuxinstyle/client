package com.game.base.account.service;

import com.game.SpringContext;
import com.game.base.account.packet.CM_CreatePlayer;
import com.game.scence.packet.CM_EnterInitScence;
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
        System.out.println("请给你的角色取个吊炸天的昵称吧：");
        String nickName = scanner.next();
        System.out.println("请请选择你要创建的职业：选择三种职业中的一种（道士:taoist/法师:mage /战士:warrior）");
        String career = scanner.next();

        CM_CreatePlayer cm  = new CM_CreatePlayer();
        cm.setAccountId(accountId);
        cm.setNickName(nickName);
        cm.setCareer(career);
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
