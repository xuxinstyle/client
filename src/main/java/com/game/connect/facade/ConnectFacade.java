package com.game.connect.facade;

import com.game.SpringContext;
import com.game.connect.packet.SM_Connect;
import com.socket.dispatcher.anno.HandlerAnno;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/5/19 11:43
 */
@Component
public class ConnectFacade {

    @HandlerAnno
    public void connect(TSession session, SM_Connect res){
        SpringContext.getConnectService().welcome(session);
    }
}
