package com.game.user.item.service;

import com.game.SpringContext;
import com.game.user.item.packet.bean.ItemVO;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：xuxin
 * @Date: 2019/6/14 15:03
 */
@Component
public class ItemServiceImpl implements ItemService {
    @Override
    public void showItems(TSession session, List<ItemVO> itemList, int size, int useSize) {
        /*if(itemList==null||itemList.isEmpty()){
            System.out.println("背包为空");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
            return;
        }*/
        System.out.println("玩家["+session.getAccountId()+"] 背包大小：["+size+"]已使用："+useSize);
        for(ItemVO itemVO:itemList){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("唯一id:["+itemVO.getObjectId()+"] ");
            stringBuffer.append("道具名：["+itemVO.getItemName()+"] ");
            stringBuffer.append("道具数量：["+itemVO.getNum()+"] " );

            System.out.println(stringBuffer.toString());
        }
        SpringContext.getScenceService().doOperate(session, session.getMapId());
    }

    @Override
    public void AwardToPack(TSession session, int status) {
        if(status == 1){
            System.out.println("添加成功");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else if(status ==2 ){
            System.out.println("添加失败");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else if(status == 3){
            System.out.println("背包已满");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }
    }
}
