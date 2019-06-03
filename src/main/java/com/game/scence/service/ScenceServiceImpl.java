package com.game.scence.service;

import com.game.scence.constant.SceneType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/3 16:41
 */
@Component
public class ScenceServiceImpl implements ScenceService{
    private static final Logger logger = LoggerFactory.getLogger(ScenceServiceImpl.class);
    @Override
    public void enterInitScence(String accountId, int type) {
        logger.info("进入了enter");

            if(type==1){
                System.out.println("欢迎进入:新手村，你终于可以开始你的吊炸天的旅程了");
                doOperating();
                return;

        }
    }

    private void doOperating() {
    }
}
