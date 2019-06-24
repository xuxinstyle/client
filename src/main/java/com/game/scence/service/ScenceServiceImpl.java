package com.game.scence.service;

import com.game.role.player.packet.CM_ShowAttribute;
import com.game.scence.constant.SceneType;
import com.game.scence.model.Position;
import com.game.scence.packet.*;
import com.game.user.equip.packet.CM_Equip;
import com.game.user.equip.packet.CM_ShowEquipInfo;
import com.game.user.equip.packet.CM_UnEquip;
import com.game.user.equipupgrade.packet.CM_EquipUpgrade;
import com.game.user.item.packet.CM_AwardToPack;
import com.game.user.item.packet.CM_ShowItemInfo;
import com.game.user.item.packet.CM_ShowPackItem;
import com.game.user.item.packet.CM_UseItem;
import com.game.user.strenequip.packet.CM_StrenEquip;
import com.game.user.strenequip.packet.SM_StrenEquip;
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
    @Override
    public void enterMap(TSession session, String accountId, String context,String strPosition, int sceneType,int x,int y) {
        String mapContext = scenceManager.getContext(sceneType);
        if(mapContext==null){
            scenceManager.put(sceneType, context);
        }
        switch (sceneType){
            case 1:
                session.setMapId(sceneType);
                scenceManager.setAccountIdsMap(sceneType, accountId, strPosition);
                System.out.println("欢迎进入:新手村");
                CM_OnlinePlayerOperate cm = new CM_OnlinePlayerOperate();
                cm.setMapId(sceneType);
                session.sendPacket(cm);

                doOperate(session, 1);
                break;
            case 2:
                session.setMapId(sceneType);
                scenceManager.setAccountIdsMap(sceneType, accountId, strPosition);
                System.out.println("欢迎进入:野外");
                CM_OnlinePlayerOperate cm1  = new CM_OnlinePlayerOperate();
                cm1.setMapId(sceneType);
                session.sendPacket(cm1);
                //showMap(sceneType,scenceManager.getPostionMap(sceneType));

                doOperate(session, 2);
                break;
                default :
                    System.out.println("进入地图失败，没有该地图信息,请重新输入指令");
                    if(session.getMapId()==0){
                        doOperate(session, 1);
                    }else{
                        doOperate(session, session.getMapId());
                    }
        }

    }

    @Override
    public void doOperate(TSession session, int mapId) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String operater = scanner.nextLine();
            if("esc".equals(operater.trim().toLowerCase())){
                session.getChannel().close();
                return;
            }
            String[] split = operater.split(" ");
            if(split.length==2){
                if("changemap".equals(split[0].trim().toLowerCase())){
                    if("1".equals(split[1].trim().toLowerCase())){
                        CM_EnterMap cm = new CM_EnterMap();
                        cm.setMapId(1);
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        break;
                    }else
                    if("2".equals(split[1].trim().toLowerCase())){
                        CM_EnterMap cm = new CM_EnterMap();
                        cm.setMapId(2);
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        break;
                    }else{
                        System.out.println("没有地图资源无法进入");
                    }

                }else if("show".equals(split[0].trim().toLowerCase())){
                    if("all".equals(split[1].trim().toLowerCase())){
                        CM_ShowAllAccountInfo cm = new CM_ShowAllAccountInfo();
                        cm.setMapId(mapId);
                        session.sendPacket(cm);
                        break;
                    }else if("myself".equals(split[1].trim().toLowerCase())){
                        CM_ShowAccountInfo cm = new CM_ShowAccountInfo();
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        break;
                    }else if("pack".equals(split[1].trim().toLowerCase())){
                        CM_ShowPackItem cm = new CM_ShowPackItem();
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        break;
                    }else if("attribute".equals(split[1].trim().toLowerCase())){

                        CM_ShowAttribute cm = new CM_ShowAttribute();
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        break;
                    }else if("equip".equals(split[1].trim().toLowerCase())){
                        CM_ShowEquipInfo cm = new CM_ShowEquipInfo();
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);
                        break;
                    }else{
                        CM_ShowAccountInfo cm = new CM_ShowAccountInfo();
                        cm.setAccountId(split[1]);
                        session.sendPacket(cm);
                        break;
                    }
                }else if("equip".equals(split[0].trim().toLowerCase())){
                    long objectId = Long.parseLong(split[1].trim().toLowerCase());
                    CM_Equip cm = new CM_Equip();
                    cm.setAccountId(session.getAccountId());
                    cm.setItemObjectId(objectId);
                    session.sendPacket(cm);
                    break;
                }else if("unequip".equals(split[0].trim().toLowerCase())){
                    int position = Integer.parseInt(split[1].trim().toLowerCase());
                    if(position<1||position>8){
                        System.out.println("装备位置错误，请输入1-8的数字");
                        continue;
                    }
                    CM_UnEquip cm = new CM_UnEquip();
                    cm.setPosition(position);
                    session.sendPacket(cm);
                    break;
                } else if("stren".equals(split[0].trim().toLowerCase())){
                    long itemObjectId = Long.parseLong(split[1].trim().toLowerCase());
                    CM_StrenEquip cm = new CM_StrenEquip();
                    cm.setAccountId(session.getAccountId());
                    cm.setItemObjectId(itemObjectId);
                    session.sendPacket(cm);
                    break;
                } else if("upgrade".equals(split[0].trim().toLowerCase())){
                    long itemObjectId = Long.parseLong(split[1].trim().toLowerCase());
                    CM_EquipUpgrade cm = new CM_EquipUpgrade();
                    cm.setEquipObjectId(itemObjectId);
                    session.sendPacket(cm);
                    break;
                }else if("use".equals(split[0].trim().toLowerCase())){
                    long itemObjectId = Long.parseLong(split[1].trim().toLowerCase());
                    CM_UseItem cm = new CM_UseItem();
                    cm.setNum(1);
                    cm.setItemObjectId(itemObjectId);
                    session.sendPacket(cm);
                    break;
                }else{
                    System.out.println("指令非法，请重新输入");
                }

            }else if(split.length==3){
                if("move".equals(split[0].trim().toLowerCase())){
                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);
                    CM_Move cm = new CM_Move();
                    cm.setMapId(session.getMapId());
                    cm.setAccountId(session.getAccountId());
                    cm.setX(x);
                    cm.setY(y);
                    session.sendPacket(cm);
                    break;
                }else if("add".equals(split[0].trim().toLowerCase())){
                    int itemModelId = Integer.parseInt(split[1]);
                    int num = Integer.parseInt(split[2]);
                    CM_AwardToPack cm = new CM_AwardToPack();
                    cm.setAccountId(session.getAccountId());
                    cm.setItemModelId(itemModelId);
                    cm.setNum(num);
                    session.sendPacket(cm);
                    break;
                }else if("use".equals(split[0].trim().toLowerCase())){
                    long itemObjectId = Long.parseLong(split[1].trim().toLowerCase());
                    int num = Integer.parseInt(split[2].trim().toLowerCase());
                    CM_UseItem cm = new CM_UseItem();
                    cm.setNum(num);
                    cm.setItemObjectId(itemObjectId);
                    session.sendPacket(cm);
                    break;
                }else if("show".equals(split[0].trim().toLowerCase())){
                    if("attribute".equals(split[1].trim().toLowerCase())){
                        CM_ShowAttribute cm = new CM_ShowAttribute();
                        cm.setAccountId(split[2].trim().toLowerCase());
                        session.sendPacket(cm);
                        break;
                    }else if("equip".equals(split[1].trim().toLowerCase())){
                        CM_ShowEquipInfo cm = new CM_ShowEquipInfo();
                        cm.setAccountId(split[2].trim().toLowerCase());
                        session.sendPacket(cm);
                        break;
                    }else if("item".equals(split[1].trim().toLowerCase())){
                        /*CM_ShowEquipInfo cm = new CM_ShowEquipInfo();
                        cm.setAccountId(split[2].trim().toLowerCase());
                        session.sendPacket(cm);
                        break;*/
                        if(!isNumeric(split[2].trim().toLowerCase())){
                            System.out.println("输入非法，请输入数字");
                            continue;
                        }
                        CM_ShowItemInfo cm = new CM_ShowItemInfo();
                        cm.setAccountId(session.getAccountId());
                        cm.setItemObjectId(Long.parseLong(split[2].trim().toLowerCase()));
                        session.sendPacket(cm);
                        //System.out.println("功能待开发。。。。");
                    }else{
                        System.out.println("指令非法，请重新输入");
                    }

                }else if("stren".equals(split[0].trim().toLowerCase())){
                    if(!isNumeric(split[1].trim().toLowerCase())&&isNumeric(split[2].trim().toLowerCase())){
                        System.out.println("输入非法，请输入数字");
                        continue;
                    }
                    System.out.println("功能待开发。。。。");
                    break;
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
    public void showAllAccount(TSession session, String context) {
        String[] split = context.split("#");
        for (String str:split){
            System.out.println(str);
        }
        doOperate(session, session.getMapId());
    }

    @Override
    public void showAccount(TSession session, SM_ShowAccountInfo sm) {
        if(sm.getAccountId()==null){
            System.out.println("地图中没有该玩家");
            doOperate(session, session.getMapId());
        }else {
            System.out.println("[" + sm.getAccountId() + "]:");
            StringBuffer str = new StringBuffer();
            str.append("昵称：[" + sm.getNickName()+"] " );
            str.append("角色名：["+sm.getPlayerName()+"] ");
            str.append("职业：[" + sm.getCareer()+"] " );
            str.append("等级：[" + sm.getLevel()+"] ");
            str.append("位置：["+sm.getX()+","+sm.getY()+"] ");
            System.out.println(str.toString());
            doOperate(session, session.getMapId());
        }
    }

    @Override
    public void move(TSession session, SM_Move sm) {
        if(sm.getStatus()==1){
            scenceManager.setAccountIdsMap(session.getMapId(),session.getAccountId(), sm.getX(),sm.getY());
            CM_OnlinePlayerOperate cm = new CM_OnlinePlayerOperate();
            cm.setMapId(session.getMapId());
            session.sendPacket(cm);
            //showMap(session.getMapId(),scenceManager.getPostionMap(session.getMapId()));
            doOperate(session, session.getMapId());
        }else {
            System.out.println("移动失败，该位置有阻挡点，不能移动");
            doOperate(session, session.getMapId());
        }
    }

    @Override
    public void showMap(TSession session, String scenePositions) {

        List<Position> list = doParsePosition(scenePositions);
        showMap(session.getMapId(), list);
    }

    private List<Position> doParsePosition(String scenePositions) {
        List<Position> list = new ArrayList<>();
        String[] positions = scenePositions.split(":");
        for(String str:positions){
            String[] split = str.split(",");
            if(split.length!=2){
                return null;
            }
            Position position = new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            list.add(position);
        }
        return list;
    }

    private void showMap(int mapId,List<Position> map) {
        String context = scenceManager.getContext(mapId);
        String[] split = context.split(",");

        String[] nowPosion = split;
        for(Position position: map){
            int x= position.getX();
            int y =position.getY();
            for(int i = 0;i<split.length;i++){
                StringBuffer nowy = new StringBuffer();
                if(y==(i)){
                    String[] mapX = split[i].split(" ");
                    for (int j = 0;j<mapX.length;j++){
                        if(j==x){
                            nowy.append("* ");
                        }else {
                            nowy.append(mapX[j] + " ");
                        }
                    }
                    nowPosion[i] = nowy.toString();
                }else{
                    nowPosion[i] = split[i];
                }
            }
        }
        System.out.println("地图："+SceneType.valueOf(mapId).getTypeName());
        for (int i = nowPosion.length-1;i>=0;i--){
            System.out.println(nowPosion[i]);
        }
    }

}
