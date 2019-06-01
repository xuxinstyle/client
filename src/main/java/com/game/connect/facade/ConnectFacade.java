package com.game.connect.facade;

import com.game.SpringContext;
import com.game.connect.packet.SM_Connect;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/5/19 11:43
 */
@Component
public class ConnectFacade {
    private static Logger logger = LoggerFactory.getLogger(ConnectFacade.class);
    @HandlerAnno
    public void connect(TSession session, SM_Connect res){
        try {
            SpringContext.getLoginService().welcome(session);
        }catch (Exception e){
            logger.error("非法错误："+e.toString());
        }

    }

}
