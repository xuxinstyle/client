package com.game.scence.facade;

import com.game.SpringContext;
import com.game.scence.packet.SM_EnterInitScence;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 14:15
 */
@Component
public class ScenceFacade {
    private static final Logger logger = LoggerFactory.getLogger(ScenceFacade.class);
    @HandlerAnno
    public void enterInitScence(TSession session, SM_EnterInitScence res){
        try {
            SpringContext.getScenceService().enterInitScence(res.getAccountId(), res.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
