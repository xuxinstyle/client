package com.game.role.player.service;

import com.game.SpringContext;
import com.game.base.attribute.Attribute;
import com.socket.core.TSession;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：xuxin
 * @Date: 2019/6/17 17:50
 */
@Component
public class PlayerServiceImpl implements PlayerService {
    @Override
    public void playerUpLevel(int status,String playerName,int proLevel, int afterLevel) {
        if(status==1) {
            System.out.println("恭喜[" + playerName + "]从[" + proLevel + "]级" + "升级到[" + afterLevel + "]级");
        }else if(status==2){
            System.out.println("达到等级上限！");
        }else{
            System.out.println("升级失败！");
        }

    }

    @Override
    public void showAttribute(TSession session, String playerName, List<Attribute> firstAttribute, List<Attribute> secondAttribute,List<Attribute> otherAttributeList ) {
        System.out.println("角色名：["+playerName+"]");

        System.out.println("-----------二级属性：-----------");
        for (Attribute attribute:secondAttribute){
            System.out.println(attribute.getAttributeType().getAttrName()+"："+attribute.getValue());
        }
        System.out.println("-----------一级属性：-----------");
        for (Attribute attribute:firstAttribute){
            System.out.println(attribute.getAttributeType().getAttrName()+"："+attribute.getValue());
        }
        System.out.println("-----------其他属性:------------");
        for (Attribute attribute:otherAttributeList){
            System.out.println(attribute.getAttributeType().getAttrName()+"："+attribute.getValue());
        }
        SpringContext.getScenceService().doOperate(session,session.getMapId());
    }
}
