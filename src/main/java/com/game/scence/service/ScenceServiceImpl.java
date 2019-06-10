package com.game.scence.service;

import com.game.scence.packet.*;
import com.socket.core.TSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

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
    public void enterMap(TSession session, String accountId, String context, int sceneType,int x,int y) {
        String mapContext = scenceManager.getContext(sceneType);
        if(mapContext==null){
            scenceManager.put(sceneType, context);
        }
        switch (sceneType){
            case 1:
                session.setMapId(sceneType);
                System.out.println("欢迎进入:新手村");
                showMap(sceneType,x,y);
                doOperate(session, 1);
                break;
            case 2:
                session.setMapId(sceneType);
                System.out.println("欢迎进入:野外");
                showMap(sceneType,x,y);
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


    private void doOperate(TSession session, int mapId) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
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
                        /*CM_EnterMap cm = new CM_EnterMap();
                        cm.setMapId(Integer.parseInt(split[1]));
                        cm.setAccountId(session.getAccountId());
                        session.sendPacket(cm);*/
                    }

                }else if("show".equals(split[0].trim().toLowerCase())){
                    if("all".equals(split[1].trim().toLowerCase())){
                        CM_ShowAllAccountInfo cm = new CM_ShowAllAccountInfo();
                        cm.setMapId(mapId);
                        session.sendPacket(cm);
                        break;
                    }else{
                        CM_ShowAccountInfo cm = new CM_ShowAccountInfo();
                        cm.setAccountId(split[1]);
                        session.sendPacket(cm);
                        break;
                    }
                }else{
                    System.out.println("指令非法，请重新输入");
                }

            }else if(split.length==3){
                if("move".equals(split[0].trim().toLowerCase())){
                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);
                    CM_Move cm = new CM_Move();
                    cm.setAccountId(session.getAccountId());
                    cm.setX(x);
                    cm.setY(y);
                    session.sendPacket(cm);
                    break;
                }else{
                    System.out.println("指令非法，请重新输入");
                }
            }else{
                System.out.println("指令非法，请重新输入");
            }
        }
    }

    @Override
    public void showAllAccount(TSession session, String context) {
        String[] split = context.split(",");
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
            System.out.println("昵称：" + sm.getNickName() + "  职业：" + sm.getCareer() + "   等级：" + sm.getLevel());
            doOperate(session, session.getMapId());
        }
    }

    @Override
    public void move(TSession session, SM_Move sm) {
        if(sm.getStatus()==1){
            showMap(session.getMapId(),sm.getX(),sm.getY());
            doOperate(session, session.getMapId());
        }else {
            System.out.println("移动失败，该位置有阻挡点，不能移动");
            doOperate(session, session.getMapId());
        }
    }

    private void showMap(int mapId,int x,int y) {
        String context = scenceManager.getContext(mapId);
        String[] split = context.split(",");
        String[] nowPosion = split;
        for(int i = 0;i<split.length;i++){
            StringBuffer nowy = new StringBuffer();
            if(y==(i+1)){
                String[] mapX = split[i].split(" ");
                for (int j = 0;j<mapX.length;j++){
                    if(j+1==x){
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
        for (int i = nowPosion.length-1;i>=0;i--){
            System.out.println(nowPosion[i]);
        }
    }

}
