package com.game.login.service;

import com.game.SpringContext;
import com.game.login.packet.CM_Login;
import com.game.scence.constant.SceneType;
import com.game.scence.packet.CM_EnterInitScence;
import com.socket.core.SessionUtil;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @Author：xuxin
 * @Date: 2019/5/29 16:28
 */
@Component
public class LoginServiceImpl implements ILoginService{
    @Override
    public void welcome(TSession session){
        while(true) {
            System.out.println("欢迎进人到游戏世界,请输入指令进行 登录/注册/退出？(输入指令：login表示登录，esc表示退出游戏,register表示注册)");
            Scanner scanner = new Scanner(System.in);
            String code = scanner.next();

            if (code.trim().toLowerCase().equals("login")) {
                System.out.println("用户名：");
                String username = scanner.next();
                System.out.println("密码：");
                String password = scanner.next();
                CM_Login cm = new CM_Login();
                cm.setUsername(username);
                cm.setPassward(password);
                session.sendPacket(cm);
                return;
            } else if (code.trim().toLowerCase().equals("esc")) {
                System.out.println("是否确认退出游戏？（yes/no）");
                String status = scanner.next();
                if (status.trim().toLowerCase().equals("yes")) {
                    session.esc();

                    return;
                } else if (status.trim().toLowerCase().equals("no")) {

                    continue;

                }
            }else if(code.trim().toLowerCase().equals("register")){
                // 注册
                SpringContext.getRegisterService().register(session);
            }
            return ;
        }

    }

    @Override
    public void doLoginAfter(TSession session , int status, String accountId) {
        if(status == 1){
            System.out.println("登录成功！");
            /**
             * 进入新手村
             */
            session.setAccountId(accountId);

            CM_EnterInitScence cm = new CM_EnterInitScence();
            cm.setAccountId(accountId);
            cm.setType(SceneType.NoviceVillage);
            session.sendPacket(cm);

        }else if(status == 0){
            System.out.println("登录失败，密码错误请重新输入账号密码");
            welcome(session);
        }
    }
}
