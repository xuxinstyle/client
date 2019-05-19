package com.game.connect.service;

import com.game.login.packet.CM_Login;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @Author：xuxin
 * @Date: 2019/5/19 12:06
 */
@Component
public class ConnectService implements IConnectService{
    @Override
    public void welcome(TSession session){
        System.out.println("欢迎进人到游戏世界请问是否登录？(输入指令：login表示登录，esc表示退出游戏)");

        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();
        if(code.trim().equals("login")){

            session.sendPacket(new CM_Login());
        }
    }
}
