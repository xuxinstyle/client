package com.game.user.item.service;

import com.game.SpringContext;
import com.game.base.attribute.Attribute;
import com.game.base.attribute.constant.AttributeType;
import com.game.role.constant.Job;
import com.game.user.equip.constant.EquipQuality;
import com.game.user.equip.constant.EquipType;
import com.game.user.item.constant.ItemType;
import com.game.user.item.packet.SM_ShowItemInfo;
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
        }else{
            System.out.println("未知错误");
            SpringContext.getScenceService().doOperate(session,session.getMapId());
        }
    }

    @Override
    public void useItem(TSession session, int status, long effectiveTime) {
        if(status==1){
            System.out.println("使用成功，有效时间增加["+effectiveTime/(1000*60)+"]分钟");
            SpringContext.getScenceService().doOperate(session,session.getMapId());
        }else if(status == 2){
            System.out.println("你没有该道具，无法使用");
            SpringContext.getScenceService().doOperate(session,session.getMapId());
        }else if(status == 3){
            System.out.println("道具不能使用！");
            SpringContext.getScenceService().doOperate(session,session.getMapId());
        }else {
            System.out.println("未知错误！");
            SpringContext.getScenceService().doOperate(session,session.getMapId());
        }
    }

    @Override
    public void effectEnd(String itemName) {
        System.out.println("道具["+itemName+"]失效");
    }

    @Override
    public void showItemInfo(TSession session, SM_ShowItemInfo sm) {
        if(sm.getStatus()==2){
            System.out.println("没有该道具");
        }
        System.out.println("唯一id：["+sm.getItemObjectId()+"] 道具名：["+sm.getItemName()+"]  数量：["+sm.getNum()+"]");
        if(sm.getItemType()==ItemType.EQUIPMENT.getId()){
            System.out.println("装备位置：["+EquipType.valueOf(sm.getEquipType()).getEquipName()+"]");
            System.out.println("装备品质：[" + EquipQuality.valueOf(sm.getEquipQuality()).getQualityName()+"]");
            System.out.println("强化次数：[+"+sm.getStrenNum()+"]");
            System.out.println("职业限制：["+Job.valueOf(sm.getJobType()).getJobName()+"]");
            System.out.println("穿戴等级：["+sm.getLimitLevel()+"]");
            System.out.println("[基础属性]：");
            List<Attribute> baseAttributeList = sm.getBaseAttributeList();
            for (Attribute attribute : baseAttributeList){
                System.out.println(attribute.getAttributeType().getAttrName()+"："+attribute.getValue());
            }

            List<Attribute> strenAttributeList = sm.getStrenAttributeList();
            if(strenAttributeList!=null&&!strenAttributeList.isEmpty()){
                System.out.println("[强化属性]：");
                for (Attribute attribute : strenAttributeList){
                    System.out.println(attribute.getAttributeType().getAttrName()+"："+attribute.getValue());
                }
            }
        }else if(sm.getItemType() == ItemType.CONSUME_STONE.getId()){
            System.out.println("用于提升装备品质装备强化或升级的宝石");
        }

    }
}
