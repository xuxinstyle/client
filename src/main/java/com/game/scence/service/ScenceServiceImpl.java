package com.game.scence.service;

import com.game.scence.constant.SceneType;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 16:41
 */
@Component
public class ScenceServiceImpl implements ScenceService{

    @Override
    public void enterInitScence(String accountId, int type) {
        for (SceneType sceneType:SceneType.values()) {
            if(sceneType.getMapid()==type){
                System.out.println("欢迎进入:"+sceneType.getName()+"，你终于可以开始你的吊炸天的旅程了");
            }
        }




    }
}
