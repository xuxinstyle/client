package com.game.login.service;

import com.game.SpringContext;
import com.game.login.packet.CM_Login;
import com.game.scence.visible.packet.CM_EnterMap;
import com.game.util.MD5Util;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @Author：xuxin
 * @Date: 2019/5/29 16:28
 */
@Component
public class LoginServiceImpl implements ILoginService {
    @Override
    public void welcome(TSession session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎进人到游戏世界,请输入指令进行 登录/注册/退出？(输入指令：login表示登录，esc表示退出游戏,register表示注册,relogin表示重新登录)");
        while (scanner.hasNext()) {
            String code = scanner.next();
            if (code.trim().toLowerCase().equals("login")) {
                System.out.println("用户名：");
                String username = scanner.next();
                System.out.println("密码：");
                String password = scanner.next();
                String passwardDB = MD5Util.inputPassToFormPass(password);
                CM_Login cm = new CM_Login();
                cm.setUsername(username);
                cm.setPassward(passwardDB);
                session.sendPacket(cm);
                session.setAccountId(username);
                session.setPassward(passwardDB);
                return;
            } else if (code.trim().toLowerCase().equals("esc")) {
                System.out.println("是否确认退出游戏？（yes/no）");
                String ask = scanner.next();
                while (true) {
                    if (ask.trim().toLowerCase().equals("yes")) {
                        session.esc();
                        return;
                    } else if (ask.trim().toLowerCase().equals("no")) {
                        continue;
                    } else {
                        System.out.println("非法指令");
                    }
                }
            } else if (code.trim().toLowerCase().equals("register")) {
                // 注册
                SpringContext.getRegisterService().register(session);
                return;
            } else if (code.trim().toLowerCase().equals("relogin")) {
                String passward = session.getPassward();
                String accountId = session.getAccountId();
                if (passward != null && accountId != null) {
                    CM_Login cm = new CM_Login();
                    cm.setUsername(accountId);
                    cm.setPassward(passward);
                    session.sendPacket(cm);
                    return;
                } else {
                    System.out.println("请重新登录");
                }
            } else {
                System.out.println("非法指令");
            }

        }

    }

    @Override
    public void doLoginAfter(TSession session, int status, String accountId, int mapId, long playerId) {
        if (status == 1) {
            System.out.println("登录成功！进入游戏。。。。");
            session.setAccountId(accountId);
            session.setPlayerId(playerId);
            CM_EnterMap cm = new CM_EnterMap();
            cm.setMapId(mapId);
            cm.setAccountId(accountId);
            session.setMapId(mapId);
            session.sendPacket(cm);

        } else if (status == 0) {
            System.out.println("登录失败");
            SpringContext.getLoginService().welcome(session);
        } else if (status == -1) {
            System.out.println("有其他设备登录账号，请重新登录");
            SpringContext.getLoginService().welcome(session);
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
        SpringContext.getLoginService().welcome(session);
    }
}
