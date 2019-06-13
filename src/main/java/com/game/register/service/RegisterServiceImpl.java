package com.game.register.service;

import com.game.register.packet.CM_Register;
import com.game.utils.MD5Util;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @Author：xuxin
 * @Date: 2019/5/29 16:36
 */
@Component
public class RegisterServiceImpl implements RegisterService {

    /**
     * 注册过程中可能存在问题，如密码不一致的情况。所以需要处理每一种可能的情况
     */
    @Override
    public void register(TSession session) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("********************注册界面********************");
            System.out.println("账号：");
            String username = scanner.next();
            System.out.println("密码：");
            String passward = scanner.next();
            System.out.println("再次输入密码：");
            String passwardAgain = scanner.next();
            if(!passward.equals(passwardAgain)){
                System.out.println("两次输入的密码不一样！请重新输入");
                continue;
            }

            String passwordMD5 = MD5Util.inputPassToFormPass(passward);
            CM_Register cm = new CM_Register();
            cm.setUsername(username);
            cm.setPassward(passwordMD5);
            session.sendPacket(cm);
            return ;
        }

    }


}
