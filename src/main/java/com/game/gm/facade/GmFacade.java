package com.game.gm.facade;

import com.game.SpringContext;
import com.game.gm.packet.SM_GMCommond;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/28 20:52
 */
@Component
public class GmFacade {
    @HandlerAnno
    public void doGm(TSession session, SM_GMCommond sm){
        int status = sm.getStatus();
        if(status==1) {
            System.out.println("GM命令执行成功");

        }else{
            System.out.println("GM命令执行失败");

        }
    }
}
