package com.game.scence.field.facade;

import com.game.SpringContext;
import com.game.scence.field.packet.SM_ShowMonsterInfo;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/7/6 0:13
 */
@Component
public class FieldFacade {
    @HandlerAnno
    void showMonster(TSession session, SM_ShowMonsterInfo sm){
        try {
            SpringContext.getFieldService().showMonster(sm);
        }catch (Exception e){
            System.out.println("查看怪物信息失败");
        }

    }
}
