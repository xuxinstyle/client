package com.game;

import com.game.connect.service.ConnectService;
import com.game.connect.service.IConnectService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author：xuxin
 * @Date: 2019/4/29 11:35
 */
@Component
public class SpringContext implements ApplicationContextAware {

    @Autowired
    public ApplicationContext applicationContext;

    public static SpringContext instance;

    @PostConstruct
    private final void init(){
        instance = this;
    }
    @Autowired
    public ServerConfigValue serverConfigValue;

    @Autowired
    public IConnectService connectService;


    public static ServerConfigValue getServerConfigValue(){
        return instance.serverConfigValue;
    }
    public static IConnectService getConnectService(){
        return instance.connectService;
    }
    @Override
    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        this.applicationContext = contex;
    }

}
