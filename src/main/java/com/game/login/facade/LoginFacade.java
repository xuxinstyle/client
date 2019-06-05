package com.game.login.facade;

import com.game.SpringContext;
import com.game.login.packet.SM_Login;
import com.game.login.packet.SM_LoginNoAcount;
import com.game.login.packet.SM_Logout;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/5/29 16:28
 */
@Component
public class LoginFacade {
    private static Logger logger = LoggerFactory.getLogger(LoginFacade.class);
    @HandlerAnno
    public void doLoginAfter(TSession session, SM_Login res){
        try {
            SpringContext.getLoginService().doLoginAfter(session, res.getStatus(), res.getAccountId());
        }catch (Exception e){
            logger.error("登录后出错"+e.toString());
            e.printStackTrace();
        }
    }
    @HandlerAnno
    public void doLogout(TSession session, SM_Logout res){
        SpringContext.getLoginService().logout();

    }
    @HandlerAnno
    public void login(TSession session, SM_LoginNoAcount res){
        SpringContext.getLoginService().loginNoAccount(session);
    }

}
