package com.game.role.skill.facade;

import com.game.SpringContext;
import com.game.role.skill.packet.*;
import com.socket.core.TSession;
import com.socket.dispatcher.anno.HandlerAnno;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 22:39
 */
@Component
public class SkillFacade {
    @HandlerAnno
    public void learnSkill(TSession session, SM_LearnSkill sm){
        if(sm.getStatus()==1){
            System.out.println("学习技能成功！");
        }else{
            System.out.println("学习技能失败！");
        }
    }
    @HandlerAnno
    public void upgradeSkill(TSession session, SM_UpgradeSkill sm){
        if(sm.getStatus()==1){
            System.out.println("升级技能成功！");
        }else{
            System.out.println("升级技能失败！");
        }
    }
    @HandlerAnno
    public void showSkillBar(TSession session, SM_ShowSkillInfo sm){
        SpringContext.getSkillService().showSkillInfo(session,sm.getPlayerId(),sm.getSkillSlotMap());
    }

    @HandlerAnno
    public void showSkillBar(TSession session, SM_ShowSkillBar sm){
        SpringContext.getSkillService().showSkillBar(session,sm.getPlayerId(),sm.getSkillBarMap());
    }

    @HandlerAnno
    public void setSkillBar(TSession session, SM_SetSkillBar sm){
        SpringContext.getSkillService().setSkillBar(session,sm.getStatus());
    }
    @HandlerAnno
    public void useSkillToMonsterRes(TSession session, SM_UseSkillToMonster sm){
        SpringContext.getSkillService().useSkillToMonsterRes(session,sm.getUnitId(),sm.getUnitName(),sm.getSkillId(),sm.getRealHurtMap());
    }
    @HandlerAnno
    public void useSkillToMonsterResErr(TSession session, SM_UseSkillToMonsterErr sm){
        SpringContext.getSkillService().useSkillToMonsterResErr(session,sm.getStatus());
    }

    @HandlerAnno
    public void creature(TSession session, SM_CreatureRevive sm){
        SpringContext.getSkillService().reviveCreature(sm.getObjectType(),sm.getObjectId(),sm.getCreatureName());

    }
    @HandlerAnno
    public void creatureDead(TSession session,SM_CreatureDead sm){
        SpringContext.getSkillService().creatureDead(sm.getMapId(),sm.getObjectType(),sm.getObjectId(),sm.getCreatureUnitname());
    }

}
