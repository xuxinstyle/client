package com.game.login.service;

import com.game.SpringContext;
import com.game.login.packet.CM_Login;
import com.game.scence.constant.SceneType;

import com.game.scence.packet.CM_EnterMap;
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
                while(true) {
                    if (status.trim().toLowerCase().equals("yes")) {
                        session.esc();
                        return;
                    } else if (status.trim().toLowerCase().equals("no")) {
                        continue;
                    }else {
                        System.out.println("非法指令");
                    }
                }
            }else if(code.trim().toLowerCase().equals("register")){
                // 注册
                SpringContext.getRegisterService().register(session);
                return;
            }else{
                System.out.println("非法指令");
            }

        }

    }

    @Override
    public void doLoginAfter(TSession session , int status, String accountId, int mapId) {
        if(status == 1){
            System.out.println("登录成功！");

            session.setAccountId(accountId);

            CM_EnterMap cm = new CM_EnterMap();
            cm.setMapId(mapId);
            cm.setAccountId(accountId);
            session.sendPacket(cm);

        }else if(status == 0){
            System.out.println("登录失败，密码错误请重新输入密码");
            welcome(session);
        }
    }

    @Override
    public void logout(TSession session) {
        System.out.println("账号在其他地方登录！");

        session.getChannel().close();

    }

    @Override
    public void loginNoAccount(TSession session) {
        System.out.println("登录失败，没有注册该账号");
        welcome(session);
    }
}
