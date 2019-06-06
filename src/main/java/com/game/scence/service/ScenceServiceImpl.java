package com.game.scence.service;

import com.game.scence.packet.CM_EnterMap;
import com.game.scence.packet.CM_ShowAccountInfo;
import com.game.scence.packet.CM_ShowAllAccountInfo;
import com.game.scence.packet.SM_ShowAccountInfo;
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
    public void enterInitScence(TSession session, String accountId, int type) {
        logger.info("进入了enter");
            switch (type){
                case 1:
                    session.setMapId(type);
                    System.out.println("欢迎进入:新手村");
                    doOperate(session, 1);
                    break;
                case 2:
                    session.setMapId(type);
                    System.out.println("欢迎进入:野外");
                    doOperate(session, 2);
                    break;
                    default :
                        System.out.println("没有该地图信息");

            }

    }

    @Override
    public void enterMap(TSession session, int mapId) {
        if(mapId==1){
            session.setMapId(mapId);
            System.out.println("欢迎进入新手村");
            doOperate(session,mapId);
        }else if(mapId == 2){
            session.setMapId(mapId);
            System.out.println("欢迎进入野外");
            doOperate(session,mapId);
        }else{
            System.out.println("进入地图失败，没有该地图，重新输入指令");
            doOperate(session, session.getMapId());
        }
    }



    private void doOperate(TSession session, int mapId) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String operater = scanner.nextLine();
            String[] split = operater.split(" ");
            if(split.length==2 ||split.length==3){
                if("changemap".equals(split[0])){
                    if("1".equals(split[1])){
                        CM_EnterMap cm = new CM_EnterMap();
                        cm.setMapId(1);
                        session.sendPacket(cm);
                        break;
                    }
                    if("2".equals(split[1])){
                        CM_EnterMap cm = new CM_EnterMap();
                        cm.setMapId(2);
                        session.sendPacket(cm);
                        break;
                    }

                }else if ("move".equals(split[0])){


                }else if("show".equals(split[0].toLowerCase())){
                    if("all".equals(split[1].toLowerCase())){
                        CM_ShowAllAccountInfo cm = new CM_ShowAllAccountInfo();
                        cm.setMapId(mapId);
                        session.sendPacket(cm);
                        break;
                    }else{
                        //if(scenceManager.getAccountIdsMap().containsKey(split[1])){
                        CM_ShowAccountInfo cm = new CM_ShowAccountInfo();
                        cm.setAccountId(split[1]);
                        session.sendPacket(cm);
                        break;
                       // }

                    }


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
        System.out.println("["+sm.getAccountId()+"]:");
        System.out.println("昵称："+sm.getNickName()+"  职业："+sm.getCareer()+"   等级："+sm.getLevel());
        doOperate(session, session.getMapId());
    }
}
