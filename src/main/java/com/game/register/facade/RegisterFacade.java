package com.game.register.facade;

import com.game.SpringContext;
import com.game.register.packet.SM_Register;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/5/29 21:22
 */
@Component
public class RegisterFacade {
    @HandlerAnno
    public void register(TSession session, SM_Register res){
        if(res.isStatus()){
            System.out.println("恭喜你注册成功，自动跳转到游戏界面");
            SpringContext.getLoginService().welcome(session);
        }else{
            System.out.println("*****用户名已存在，注册失败！请重新login注册*****");
            SpringContext.getRegisterService().register(session);
        }
    }
}
