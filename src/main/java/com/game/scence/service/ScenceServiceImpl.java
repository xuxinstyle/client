package com.game.scence.service;

import com.game.scence.packet.CM_ChangeMap;
import com.socket.core.TSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 16:41
 */
@Component
public class ScenceServiceImpl implements ScenceService{
    private static final Logger logger = LoggerFactory.getLogger(ScenceServiceImpl.class);
    @Override
    public void enterInitScence(TSession session, String accountId, int type) {
        logger.info("进入了enter");
            switch (type){
                case 1:System.out.println("欢迎进入:新手村");
                    doOperate(session);
                    break;
                case 2:
                    System.out.println("欢迎进入:野外");
                    doOperate(session);
                    break;
                    default :
                        System.out.println("没有该地图信息");
                        doOperate(session);

            }

    }

    @Override
    public void enterMap(TSession session, int mapId) {
        if(mapId==1){
            System.out.println("欢迎进入新手村");
        }else if(mapId == 2){
            System.out.println("欢迎进入野外");
        }
    }

    private void doOperate(TSession session) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String operater = scanner.next();
            String[] split = operater.split(" ");
            if(split.length!=2||split.length!=3){
                System.out.println("指令非法，请重新输入");
            }else{
                if("changemap".equals(split[0])){
                    if("1".equals(split[1])){
                        CM_ChangeMap cm = new CM_ChangeMap();
                        cm.setMapId(1);
                        session.sendPacket(cm);
                        break;
                    }
                    if("2".equals(split[1])){
                        CM_ChangeMap cm = new CM_ChangeMap();
                        cm.setMapId(2);
                        session.sendPacket(cm);
                        break;
                    }

                }else if ("move".equals(split[0])){


                }else{
                    System.out.println("指令非法，请重新输入");
                }
            }
        }
    }
}
