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
        for(EquipmentVO equipmentVO:positionEquipment.values()){

            System.out.println("装备位置：["+equipmentVO.getPosition()+"]["+EquipType.valueOf(equipmentVO.getPosition()).getEquipName()+"]");
            if(equipmentVO.getEquipName()==null){
                System.out.println("[无装备]");
                continue;
            }
            System.out.println("装备品质：[" + EquipQuality.valueOf(equipmentVO.getQuality()).getQualityName()+"]");
            System.out.println("装备名称：["+equipmentVO.getEquipName()+"]");
            System.out.println("职业限制：["+ Job.valueOf(equipmentVO.getJob()).getJobName()+"]");
            System.out.println("强化次数：[+"+equipmentVO.getLevel()+"]");
            System.out.println("[基础属性]：");
            List<Attribute> attributeList = equipmentVO.getAttributeList();
            if(attributeList==null){
                continue;
            }
            for(Attribute attribute :attributeList){
                System.out.println(attribute.getAttributeType().getAttrName()+": "+attribute.getValue());
            }
            List<Attribute> strenAttributeList = equipmentVO.getStrenAttributeList();
            if(!strenAttributeList.isEmpty()){
                System.out.println("[强化属性]：");
                for(Attribute attribute :strenAttributeList){
                    System.out.println(attribute.getAttributeType().getAttrName()+": "+attribute.getValue());
                }
            }
            System.out.println("-------------------------");
        }
        SpringContext.getScenceService().doOperate(session,session.getMapId());
    }
}
