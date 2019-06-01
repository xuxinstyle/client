package com.game.login.facade;

import com.game.SpringContext;
import com.game.login.packet.SM_Login;
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
            SpringContext.getLoginService().doLoginAfter(session, res.getStatus());
        }catch (Exception e){
            logger.error("非法错误"+e.toString());

        }


    }
}
