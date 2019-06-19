package com.game.user.equip.service;

import com.game.SpringContext;
import com.game.base.attribute.Attribute;
import com.game.role.constant.Job;
import com.game.user.equip.constant.EquipQuality;
import com.game.user.equip.constant.EquipType;
import com.game.user.equip.packet.bean.EquipmentVO;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/6/17 11:24
 */
@Component
public class EquipServiceImpl  implements EquipService{
    @Override
    public void equip(TSession session, int status) {
        if(status==1){
            System.out.println("穿戴成功！");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else if(status == 2){
            System.out.println("穿戴失败！");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else if(status==3){
            System.out.println("穿戴失败！没有该道具");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else if(status ==4){
            System.out.println("背包已满，不能卸下装备");
        }else {
            System.out.println("穿戴时发生错误！");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }

    }

    @Override
    public void unEquip(TSession session,int status) {
        if(status==1){
            System.out.println("卸下装备成功！");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else if(status == 3){
            System.out.println("装备栏没有装备");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else if(status ==2){
            System.out.println("背包满了无法卸下装备！");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }else{
            System.out.println("非法错误");
            SpringContext.getScenceService().doOperate(session, session.getMapId());
        }
    }

    @Override
    public void showEquipInfo(TSession session, long playerId, Map<Integer, EquipmentVO> positionEquipment, String playerName, int job) {
        System.out.println("角色唯一id ["+playerId+"] "+"角色名：["+playerName+"] "+"职业：["+Job.getJobNameByType(job)+"]");
        for(Map.Entry<Integer, EquipmentVO> entry:positionEquipment.entrySet()){
            EquipmentVO value = entry.getValue();
            System.out.println("装备位置：["+EquipType.valueOf(entry.getKey()).getEquipName()+"]");
            if(value.getEquipName()==null){
                System.out.println("无装备");
                continue;
            }

            System.out.println("装备品质：[" + EquipQuality.valueOf(value.getQuality()).getQualityName()+"]");
            System.out.println("装备名称：["+value.getEquipName()+"]");
            System.out.println("职业限制：["+ Job.valueOf(value.getJob()).getJobName()+"]");
            System.out.println("使用等级：["+value.getLevel()+"]");
            System.out.println("属性：");
            List<Attribute> attributeList = value.getAttributeList();
            if(attributeList==null){
                continue;
            }
            for(Attribute attribute :attributeList){
                System.out.println(attribute.getAttributeType().getAttrName()+": "+attribute.getValue());
            }
        }
        SpringContext.getScenceService().doOperate(session,session.getMapId());
    }
}
