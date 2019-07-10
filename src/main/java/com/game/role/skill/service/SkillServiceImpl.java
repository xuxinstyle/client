package com.game.role.skill.service;

import com.game.base.gameobject.ObjectType;
import com.game.role.skill.constant.SkillType;
import com.game.role.skill.model.SkillSlot;
import com.game.role.skill.packet.CM_ShowSkillBar;
import com.game.role.skill.packet.CM_UseAoeSkill;
import com.game.role.skill.packet.CM_UseSkillToMonster;
import com.game.role.skill.resource.SkillResource;
import com.game.scence.visible.model.Position;
import com.game.util.ScannerUtil;
import com.socket.core.SessionUtil;
import com.socket.core.TSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

/**
 * @Author：xuxin
 * @Date: 2019/7/8 23:38
 */
@Component
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillManager skillManager;

    @Override
    public void showSkillInfo(TSession session, long playerId, Map<Integer, SkillSlot> skillSlotMap){
        System.out.println("["+playerId+"]");
        for(Map.Entry<Integer, SkillSlot> entry :skillSlotMap.entrySet()){
            int skillId = entry.getKey();
            SkillResource skillResource = skillManager.getSkillResource(skillId);

            System.out.println("["+skillId+"]"+" ["+skillResource.getSkillName()+"]:");
            if(entry.getValue().isCanUse()){
                System.out.println("已激活");
                System.out.println("等级:["+entry.getValue().getLevel()+"]");
            }else{
                System.out.println("未激活");
            }
        }
    }

    @Override
    public void showSkillBar(TSession session, long playerId, Map<Integer, Integer> skillBarMap) {
        System.out.println("[" + playerId +"] ");
        for (Map.Entry<Integer, Integer> entry:skillBarMap.entrySet()){
            int skillBarId = entry.getKey();
            int skillId = entry.getValue();
            skillManager.setSkillBarMap(skillBarMap);
            SkillResource skillResource = skillManager.getSkillResource(skillId);
            if(skillResource!=null) {
                System.out.println("技能栏id[" + skillBarId + "] 技能id["+skillId+"]" + "[" + skillResource.getSkillName() + "] ");
            }else{
                System.out.println("["+ skillBarId+"] "+" [无技能]");
            }

        }
    }

    @Override
    public void setSkillBar(TSession session, int status) {
        if(status==1) {
            System.out.println("设置成功！");
        }else{
            System.out.println("设置失败！");
        }
    }

    @Override
    public void useSkill(TSession session, int skillBarId) {
        Map<Integer, Integer> skillBarMap = skillManager.getSkillBarMap();
        if(skillBarMap==null){
            CM_ShowSkillBar cm = new CM_ShowSkillBar();
            cm.setPlayerId(session.getPlayerId());
            session.sendPacket(cm);
            System.out.println("已刷新技能栏请重新使用技能");
            return;
        }
        if(skillBarMap.get(skillBarId)==null||skillBarMap.get(skillBarId)<=0){
            System.out.println("你的快捷技能栏中没有设置技能");
            return;
        }
        int skillId = skillManager.getSkillBarMap().get(skillBarId);
        SkillResource skillResource = skillManager.getSkillResource(skillId);
        SkillType skillType = SkillType.valueOf(skillResource.getSkillType());
        skillType.useSkill(skillBarId);
    }

    @Override
    public void useSingleSkill(int skillBarId){
        Scanner scan = new Scanner(System.in);
        CM_UseSkillToMonster cm = new CM_UseSkillToMonster();
        System.out.println("请输入使用目标id或输入esc取消施法：");
        while(scan.hasNext()) {
            String targetId = scan.next();
            if("esc".equals(targetId.trim().toLowerCase())){
                System.out.println("已取消技能释放");
                return;
            }
            if (!ScannerUtil.isNumeric(targetId)) {
                System.out.println("输入的目标id不是数字请重新输入");
                continue;
            }
            TSession session = SessionUtil.getSession();
            cm.setSkillBarId(skillBarId);
            cm.setMapId(session.getMapId());
            cm.setSceneId(session.getSceneId());
            cm.setSkillTargetId(Long.parseLong(targetId));
            cm.setUseId(session.getPlayerId());
            session.sendPacket(cm);
            return;
        }
    }
    @Override
    public void useAoeSkill(int skillBarId){
        Scanner scan = new Scanner(System.in);
        CM_UseAoeSkill cm = new CM_UseAoeSkill();
        System.out.println("请输入使用的目标位置或输入esc取消技能释放：");
        while(scan.hasNext()) {
            String targetpos = scan.nextLine();
            if("esc".equals(targetpos.trim().toLowerCase())){
                System.out.println("已取消技能释放");
                return;
            }
            String[] split = targetpos.split(" ");
            if(split.length!=2){
                System.out.println("输入错误，请重新输入或输入esc取消释放技能");
                continue;
            }
            if(!ScannerUtil.isNumeric(split[0])||!ScannerUtil.isNumeric(split[1])){
                System.out.println("输入位置错误");
                continue;
            }
            Position position = Position.valueOf(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            cm.setPosition(position);
            TSession session = SessionUtil.getSession();

            session.sendPacket(cm);
            return;
        }
    }

    @Override
    public void useSkillToMonsterRes(TSession session,long unitId,String unitName,int skillId, Map<Long, Long> hurtMap) {
        System.out.println("["+unitId+"] ["+unitName+"]使用了["+skillId+"]技能");
        for(Map.Entry<Long, Long> entry:hurtMap.entrySet()) {
            long targetId = entry.getKey();
            long realHurt = entry.getValue();
            System.out.println("[" + targetId + "]受到[" + realHurt + "]点伤害");
        }
    }

    @Override
    public void useSkillToMonsterResErr(TSession session, int status) {
        if(status==2){
            System.out.println("地图中没有目标！");
        }else if(status == 3){
            System.out.println("你的技能栏中没有技能！");
        }else if(status == 4){
            System.out.println("目标太远，无法施法");
        }else if(status == 5){
            System.out.println("该地图不能释放技能");
        }else if(status == 6){
            System.out.println("你已经死了");
        }else{
            System.out.println("未知错误");
        }
    }

    @Override
    public void reviveCreature(ObjectType objectType, long objectId ,String creatureUnitName) {
        System.out.println(objectType.getObjectName()+":["+objectId+"] ["+creatureUnitName+"]复活");
    }

    @Override
    public void creatureDead(int mapId, ObjectType objectType, long objectId, String creatureUnitname) {
        System.out.println(objectType.getObjectName()+":["+objectId+"] ["+creatureUnitname+"]死亡");
    }
}
