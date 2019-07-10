package com.game.scence.visible.service;

import com.game.SpringContext;
import com.game.base.attribute.Attribute;
import com.game.base.attribute.constant.AttributeType;
import com.game.base.gameobject.ObjectType;
import com.game.gm.packet.CM_GMCommond;
import com.game.role.constant.Job;
import com.game.role.player.packet.CM_ShowAttribute;
import com.game.role.skill.packet.*;
import com.game.scence.field.packet.CM_ShowMonsterInfo;
import com.game.scence.npc.resource.NpcResource;
import com.game.scence.visible.constant.SceneType;
import com.game.scence.visible.model.Position;
import com.game.scence.visible.packet.*;
import com.game.role.equip.packet.CM_Equip;
import com.game.role.equip.packet.CM_ShowEquipInfo;
import com.game.role.equip.packet.CM_UnEquip;
import com.game.role.equipupgrade.packet.CM_EquipUpgrade;
import com.game.scence.visible.packet.bean.VisibleVO;
import com.game.user.item.packet.CM_ShowItemInfo;
import com.game.user.item.packet.CM_ShowPackItem;
import com.game.user.item.packet.CM_UseItem;
import com.game.role.strenequip.packet.CM_StrenEquip;
import com.resource.core.StorageManager;
import com.socket.core.TSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 16:41
 */
@Component
public class ScenceServiceImpl implements ScenceService{
    private static final Logger logger = LoggerFactory.getLogger(ScenceServiceImpl.class);
    @Autowired
    private ScenceManager scenceManager;
    @Autowired
    private StorageManager storageManager;
    @Override
    public void init() {
        scenceManager.init();
    }

    @Override
    public void changeMap(TSession session, int preMapId, int targetmapId) {
        System.out.println("从"+SceneType.valueOf(preMapId).getTypeName()+"进入到"+SceneType.valueOf(targetmapId).getTypeName()+"成功");
        session.sendPacket(CM_ScenePositionVisible.valueOf(targetmapId));
    }

    @Override
    public void changeMapErr(TSession session,int status) {
        if(status==2) {
            System.out.println("切换地图失败！");
            SpringContext.getScenceService().doOperate(session);
        }else if(status == 3){
            System.out.println("您已经在目标地图了无法切换！");
            SpringContext.getScenceService().doOperate(session);
        }
    }

    @Override
    public void enterMapErr() {
        System.out.println("进入地图失败");
    }

    @Override
    public void enterMap(TSession session, String accountId,  int mapId, int sceneId) {
        switch (mapId){
            case 1:
                session.setMapId(mapId);
                session.setSceneId(sceneId);
                scenceManager.setAccountIdsMap(mapId, accountId);
                System.out.println("欢迎进入:新手村");
                CM_ScenePositionVisible cm = new CM_ScenePositionVisible();
                cm.setMapId(mapId);
                session.sendPacket(cm);
                break;
            case 2:
                session.setMapId(mapId);
                session.setSceneId(sceneId);
                scenceManager.setAccountIdsMap(mapId, accountId);
                System.out.println("欢迎进入:野外");
                CM_ScenePositionVisible cm1  = new CM_ScenePositionVisible();
                cm1.setMapId(mapId);
                session.sendPacket(cm1);

                break;
            default :
                System.out.println("进入地图失败，没有该地图信息,请重新输入指令");
                if(session.getMapId()==0){

                }else{

                }
        }
        SpringContext.getScenceService().doOperate(session);
    }


    @Override
    public void doOperate(TSession session) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {

            String operater = scanner.nextLine();
            if("esc".equals(operater.trim().toLowerCase())){
                session.getChannel().close();
                return;
            }
            String[] command = operater.split(":");
            if(command.length==2&&command[0].toLowerCase().equals("gm")){
                CM_GMCommond cm = new CM_GMCommond();
                cm.setCommand(command[1]);
                session.sendPacket(cm);
                continue;
            }
            String[] split = operater.split(" ");
            if(split.length==2){
                if("set".equals(split[0].trim().toLowerCase())){
                    CM_SetSkillBar cm = new CM_SetSkillBar();
                    cm.setSetStr(split[1].trim().toLowerCase());
                    cm.setPlayerId(session.getPlayerId());
                    session.sendPacket(cm);
                }else if("changemap".equals(split[0].trim().toLowerCase())){
                    if("1".equals(split[1].trim().toLowerCase())){
                        if(!isNumeric(split[1].trim().toLowerCase())){
                            System.out.println("输入的地图id不是数字");
                            continue;
                        }
                        CM_ChangeMap cm = new CM_ChangeMap();
                        cm.setTargetMapId(1);
                        session.sendPacket(cm);
                        return;
                    }else if("2".equals(split[1].trim().toLowerCase())){
                        if(!isNumeric(split[1].trim().toLowerCase())){
                            System.out.println("输入的地图id不是数字");
                            continue;
                        }
                        CM_ChangeMap cm = new CM_ChangeMap();
                        cm.setTargetMapId(2);
                        session.sendPacket(cm);
                        return;
                    }else{
                        if(!isNumeric(split[1].trim().toLowerCase())){
                            System.out.println("输入的地图id不是数字");
                            continue;
                        }
                        CM_ChangeMap cm = new CM_ChangeMap();
                        cm.setTargetMapId(Integer.parseInt(split[1].trim().toLowerCase()));
                        session.sendPacket(cm);
                        return;
                    }

                }else if("show".equals(split[0].trim().toLowerCase())){
                    if("quickskill".equals(split[1].trim().toLowerCase())){
                        CM_ShowSkillBar cm = new CM_ShowSkillBar();
                        cm.setPlayerId(session.getPlayerId());
                        session.sendPacket(cm);
                    }else if("skill".equals(split[1].trim().toLowerCase())){
                        CM_ShowSkillInfo cm = new CM_ShowSkillInfo();
                        cm.setPlayerId(session.getPlayerId());
                        session.sendPacket(cm);
                    }else if("all".equals(split[1].trim().toLowerCase())){
                        CM_ShowAllVisibleInfo cm = new CM_ShowAllVisibleInfo();
                        cm.setMapId(session.getMapId());
                        session.sendPacket(cm);
                        continue;
                    }else if("myself".equals(split[1].trim().toLowerCase())){
                        CM_ShowAccountInfo cm = new CM_ShowAccountInfo();
                        cm.setMapId(session.getMapId());
                        cm.setObjectId(session.getPlayerId());
                        session.sendPacket(cm);
                        continue;
                    }else if("pack".equals(split[1].trim().toLowerCase())){
                        CM_ShowPackItem cm = new CM_ShowPackItem();
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        continue;
                    }else if("attribute".equals(split[1].trim().toLowerCase())){

                        CM_ShowAttribute cm = new CM_ShowAttribute();
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        continue;
                    }else if("equip".equals(split[1].trim().toLowerCase())){
                        CM_ShowEquipInfo cm = new CM_ShowEquipInfo();
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        continue;
                    }else{
                        if(!isNumeric(split[1].trim().toLowerCase())){
                            System.out.println("输入的角色唯一id不是数字");
                            continue;
                        }
                        /**
                         * 查看玩家信息
                         */
                        CM_ShowAccountInfo cm = new CM_ShowAccountInfo();
                        cm.setObjectId(Long.parseLong(split[1]));
                        cm.setMapId(session.getMapId());
                        session.sendPacket(cm);
                        continue;
                    }
                }else if("equip".equals(split[0].trim().toLowerCase())){
                    if(!isNumeric(split[1].trim().toLowerCase())){
                        System.out.println("输入的道具id不是数字");
                        continue;
                    }
                    long objectId = Long.parseLong(split[1].trim().toLowerCase());
                    CM_Equip cm = new CM_Equip();
                    cm.setAccountId(session.getAccountId());
                    cm.setItemObjectId(objectId);
                    session.sendPacket(cm);
                    continue;
                }else if("unequip".equals(split[0].trim().toLowerCase())){
                    if(!isNumeric(split[1].trim().toLowerCase())){
                        System.out.println("装备位置输入错误，请输入1-8的数字");
                        continue;
                    }
                    int position = Integer.parseInt(split[1].trim().toLowerCase());
                    if(position<1||position>8){
                        System.out.println("装备位置输入错误，请输入1-8的数字");
                        continue;
                    }
                    CM_UnEquip cm = new CM_UnEquip();
                    cm.setPosition(position);
                    session.sendPacket(cm);
                    continue;
                } else if("stren".equals(split[0].trim().toLowerCase())){
                    String s = split[1].trim().toLowerCase();
                    if(isNumeric(s)) {
                        long itemObjectId = Long.parseLong(s);
                        CM_StrenEquip cm = new CM_StrenEquip();
                        cm.setAccountId(session.getAccountId());
                        cm.setItemObjectId(itemObjectId);
                        session.sendPacket(cm);
                    }
                    continue;
                } else if("upgrade".equals(split[0].trim().toLowerCase())){
                    String s = split[1].trim().toLowerCase();
                    if(isNumeric(s)) {
                        long itemObjectId = Long.parseLong(s);
                        CM_EquipUpgrade cm = new CM_EquipUpgrade();
                        cm.setEquipObjectId(itemObjectId);
                        session.sendPacket(cm);
                    }
                    continue;
                }else if("use".equals(split[0].trim().toLowerCase())){
                    String s = split[1].trim().toLowerCase();
                    if(isNumeric(s)) {
                        long itemObjectId = Long.parseLong(split[1].trim().toLowerCase());
                        CM_UseItem cm = new CM_UseItem();
                        cm.setNum(1);
                        cm.setItemObjectId(itemObjectId);
                        session.sendPacket(cm);
                    }
                    continue;
                }else{
                    System.out.println("指令非法，请重新输入");
                }
            }else if(split.length==3){
                if("upgrade".equals(split[0].trim().toLowerCase())){
                    if("skill".equals(split[1].trim().toLowerCase())){
                        if(!isNumeric(split[2].trim().toLowerCase())){
                            System.out.println("输入的技能id错误！");
                            continue;
                        }
                        CM_UpgradeSkill cm = new CM_UpgradeSkill();
                        cm.setSkillId(Integer.parseInt(split[2].trim().toLowerCase()));
                        cm.setPlayerId(session.getPlayerId());
                        session.sendPacket(cm);
                    }
                }else if("learn".equals(split[0].trim().toLowerCase())){
                    if("skill".equals(split[1].trim().toLowerCase())){
                        if(!isNumeric(split[2].trim().toLowerCase())){
                            System.out.println("输入的技能id错误！");
                            continue;
                        }
                        CM_LearnSkill cm = new CM_LearnSkill();
                        cm.setPlayerId(session.getPlayerId());
                        cm.setSkillId(Integer.parseInt(split[2].trim().toLowerCase()));
                        session.sendPacket(cm);
                    }
                }else if("move".equals(split[0].trim().toLowerCase())){
                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);
                    CM_Move cm = new CM_Move();
                    cm.setMapId(session.getMapId());
                    cm.setAccountId(session.getAccountId());
                    cm.setTargetPos(Position.valueOf(x,y));
                    session.sendPacket(cm);
                    continue;
                }else if("add".equals(split[0].trim().toLowerCase())){
                    /*int itemModelId = Integer.parseInt(split[1]);
                    int num = Integer.parseInt(split[2]);
                    CM_AwardToPack cm = new CM_AwardToPack();
                    cm.setAccountId(session.getAccountId());
                    cm.setItemModelId(itemModelId);
                    cm.setNum(num);
                    session.sendPacket(cm);*/
                    System.out.println("请使用gm命令！");
                    continue;
                }else if("use".equals(split[0].trim().toLowerCase())){
                    if("skill".equals(split[1].trim().toLowerCase())){
                        if(!isNumeric(split[2].trim().toLowerCase())){
                            System.out.println("输入非法，请输入数字");
                            continue;
                        }
                        int skillId = Integer.parseInt(split[2].trim().toLowerCase());
                        SpringContext.getSkillService().useSkill(session,skillId);
                    }else {
                        long itemObjectId = Long.parseLong(split[1].trim().toLowerCase());
                        int num = Integer.parseInt(split[2].trim().toLowerCase());
                        CM_UseItem cm = new CM_UseItem();
                        cm.setNum(num);
                        cm.setItemObjectId(itemObjectId);
                        session.sendPacket(cm);
                        continue;
                    }
                }else if("show".equals(split[0].trim().toLowerCase())){
                    if(!isNumeric(split[2].trim().toLowerCase())){
                        System.out.println("输入非法，请输入数字");
                        continue;
                    }
                    if("attribute".equals(split[1].trim().toLowerCase())){
                        CM_ShowAttribute cm = new CM_ShowAttribute();
                        cm.setAccountId(split[2].trim().toLowerCase());
                        session.sendPacket(cm);
                        continue;
                    }else if("equip".equals(split[1].trim().toLowerCase())){
                        CM_ShowEquipInfo cm = new CM_ShowEquipInfo();
                        cm.setAccountId(split[2].trim().toLowerCase());
                        session.sendPacket(cm);
                        continue;
                    }else if("item".equals(split[1].trim().toLowerCase())){


                        CM_ShowItemInfo cm = new CM_ShowItemInfo();
                        cm.setAccountId(session.getAccountId());
                        cm.setItemObjectId(Long.parseLong(split[2].trim().toLowerCase()));
                        session.sendPacket(cm);
                    }else if("monster".equals(split[1].trim().toLowerCase())){
                        /**
                         * 怪物
                         */
                        if(!isNumeric(split[2].trim().toLowerCase())){
                            System.out.println("输入怪物唯一id不是数字");
                            continue;
                        }
                        CM_ShowMonsterInfo cm = new CM_ShowMonsterInfo();
                        cm.setMapId(session.getMapId());
                        cm.setMonsterObjectId(Long.parseLong(split[2]));
                        session.sendPacket(cm);

                    }else if("NPC".equals(split[1].trim().toLowerCase())){
                        /**
                         * 暂时不查看npc信息
                         */
                        /*CM_ShowAccountInfo cm = new CM_ShowAccountInfo();
                        cm.setObjectId(Long.parseLong(split[2]));
                        session.sendPacket(cm);*/
                    }else if("skill".equals(split[1].trim().toLowerCase())){
                        if(!isNumeric(split[2].trim().toLowerCase())){
                            System.out.println("输入怪物唯一id不是数字");
                            continue;
                        }
                        CM_ShowSkillInfo cm = new CM_ShowSkillInfo();
                        cm.setPlayerId(Long.parseLong(split[2].trim().toLowerCase()));
                        session.sendPacket(cm);
                    }else if("quickskill".equals(split[1].trim().toLowerCase())){
                        if(!isNumeric(split[2].trim().toLowerCase())){
                            System.out.println("输入的角色id不是数字");
                            continue;
                        }
                        CM_ShowSkillBar cm = new CM_ShowSkillBar();
                        cm.setPlayerId(Long.parseLong(split[2].trim().toLowerCase()));
                        session.sendPacket(cm);
                    }else {
                        System.out.println("指令非法，请重新输入");
                    }

                }else if("stren".equals(split[0].trim().toLowerCase())){
                    if(!isNumeric(split[1].trim().toLowerCase())&&isNumeric(split[2].trim().toLowerCase())){
                        System.out.println("输入非法，请输入数字");
                        continue;
                    }
                    System.out.println("功能待开发。。。。");
                    continue;
                }else{
                    System.out.println("指令非法，请重新输入");
                }
            }else{
                System.out.println("指令非法，请重新输入");
            }
        }
    }
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    @Override
    public void showAllVisible(TSession session, List<VisibleVO> visibleVOList) {
        showNpc(session.getMapId());
        for(VisibleVO visibleVO:visibleVOList){
            StringBuffer strbuf = new StringBuffer();
            strbuf.append("唯一id：["+visibleVO.getObjectId()+"] ");
            strbuf.append("名字：["+visibleVO.getVisibleName()+"] ");
            strbuf.append("类型：["+visibleVO.getType().getObjectName()+"] ");
            strbuf.append("位置：["+visibleVO.getPosition().getX()+","+visibleVO.getPosition().getY()+"] ");
            System.out.println(strbuf.toString());
        }
    }

    private void showNpc(int mapId) {
        List<Integer> npcIds = scenceManager.getNpcIds(mapId);
        if(npcIds==null||npcIds.size()==0){
            return;
        }
        for(int id:npcIds){
            NpcResource resource = storageManager.getResource(id, NpcResource.class);
            StringBuffer strbuf = new StringBuffer();
            strbuf.append("NPC:["+resource.getName()+"] ");
            strbuf.append("npc标识id:["+resource.getId()+"] ");
            strbuf.append(" 位置：["+resource.getPx()+","+resource.getPy()+"] \n");
            strbuf.append("对话:"+resource.getChat());
            System.out.println(strbuf.toString());
        }
    }

    @Override
    public void showAccount(TSession session, SM_ShowAccountInfo sm) {
        Map<AttributeType, Attribute> attrMap = new HashMap<>();
        for(Attribute attr:sm.getAttributeList()){
            attrMap.put(attr.getType(),attr);
        }

        if(sm.getAccountId()==null){
            System.out.println("地图中没有该玩家");
        }else {
            System.out.println("[" + sm.getAccountId() + "]:");
            StringBuffer str = new StringBuffer();
            str.append("账号ID：["+sm.getAccountId()+"] ");
            str.append("角色名：["+sm.getPlayerName()+"] ");
            str.append("职业：[" + Job.valueOf(sm.getCareer()).getJobName()+"] " );
            str.append("等级：[" + sm.getLevel()+"] ");
            str.append("位置：["+sm.getPosition().getX()+","+sm.getPosition().getY()+"] ");
            System.out.println(str.toString());
            System.out.println("当前血量："+sm.getCurrHp()+"/"+attrMap.get(AttributeType.MAX_HP).getValue());
            System.out.println("当前蓝量："+sm.getCurrMp()+"/"+attrMap.get(AttributeType.MAX_MP).getValue());

            System.out.println("属性：");
            List<Attribute> attributeList = sm.getAttributeList();
            for (Attribute attr:attributeList){
                System.out.println(attr.getType().getAttrName()+":"+attr.getValue());
            }


        }
    }

    @Override
    public void move(TSession session, SM_Move sm) {
        if(sm.getStatus()==1){
            scenceManager.setAccountIdsMap(session.getMapId(),session.getAccountId(), sm.getPosition().getX(),sm.getPosition().getY());
            CM_ScenePositionVisible cm = new CM_ScenePositionVisible();
            cm.setMapId(session.getMapId());
            session.sendPacket(cm);
        }else {
            System.out.println("移动失败，该位置有阻挡点，不能移动");

        }
    }

    @Override
    public void showMap(TSession session, Map<Integer ,List<Position>> positionMap) {

        showMap(session.getMapId(), positionMap);

    }

    // 地图中 0 为空地， 1 为障碍 2 为玩家 3 为npc
    private void showMap(int mapId,Map<Integer ,List<Position>> positionMap) {
        int[][] context = scenceManager.getContext(mapId);
        int[][] playerMap = copy(context);
        if(positionMap!=null){
            for(Map.Entry<Integer ,List<Position>> entry:positionMap.entrySet()){
                for(Position position:entry.getValue()){
                    playerMap[position.getX()][position.getY()] = entry.getKey();
                }
            }
        }

        System.out.println("地图："+SceneType.valueOf(mapId).getTypeName());
        for(int x =playerMap.length-1; x>=0; x--){
            for(int y = 0;y<playerMap[0].length;y++){
                if(playerMap[x][y] == ObjectType.PLAYER.getTypeId()){
                    System.out.print("* ");
                }else if(playerMap[x][y] == ObjectType.NPC.getTypeId()){
                    System.out.print("# ");
                }else if(playerMap[x][y] == -1){
                    System.out.print("@ ");
                }else if(playerMap[x][y]==ObjectType.MONSTER.getTypeId()){
                    System.out.print("M ");
                }else{
                    System.out.print(playerMap[x][y]+" ");
                }
            }
            System.out.println();
        }
    }

    private int[][] copy(int[][] context) {
        int[][] playerMap = new int[context.length][context[0].length];
        for (int i = 0;i<playerMap.length;i++){
            for (int j = 0;j<playerMap[0].length;j++){
                playerMap[i][j] = context[i][j];
            }
        }
        return playerMap;
    }

}
