package com.game.scence.field.service;

import com.game.base.attribute.Attribute;
import com.game.base.attribute.constant.AttributeType;
import com.game.scence.field.packet.SM_ShowMonsterInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/7/6 0:14
 */
@Component
public class FieldServiceImpl implements FieldService{

    @Override
    public void showMonster(SM_ShowMonsterInfo sm) {
        Map<AttributeType, Attribute> attributeMap = new HashMap<>();
        for(Attribute attribute:sm.getAttributeList()){
            attributeMap.put(attribute.getType(),attribute);
        }
        StringBuffer strbuf = new StringBuffer();
        strbuf.append("怪物唯一ID:["+sm.getMonsterObjectId()+"] ");
        strbuf.append("名称：["+sm.getName()+"] \n");
        strbuf.append("怪物当前血量："+sm.getCurrHp()+"/"+attributeMap.get(AttributeType.MAX_HP).getValue()+"\n");
        strbuf.append("怪物当前蓝量："+sm.getCurrMp()+"/"+attributeMap.get(AttributeType.MAX_MP).getValue()+"\n");
        strbuf.append("属性:\n");
        for(Attribute attribute:sm.getAttributeList()){
            strbuf.append(attribute.getType().getAttrName()+"："+attribute.getValue()+"\n");
        }
        System.out.print(strbuf.toString());
    }
}
