package com.game;

import com.game.base.account.service.AccountService;
import com.game.connect.service.IConnectService;
import com.game.login.service.ILoginService;
import com.game.register.service.RegisterService;
import com.game.scence.service.ScenceService;
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
    @Autowired
    public ILoginService loginService;
    @Autowired
    public RegisterService registerService;
    @Autowired
    public AccountService accountService;
    @Autowired
    public ScenceService scenceService;

    public static ScenceService getScenceService(){
        return instance.scenceService;
    }

    public static AccountService getAccountService(){
        return instance.accountService;
    }

    public static ServerConfigValue getServerConfigValue(){
        return instance.serverConfigValue;
    }

    public static IConnectService getConnectService(){
        return instance.connectService;
    }

    public static ILoginService getLoginService(){
        return instance.loginService;
    }

    public static RegisterService getRegisterService(){
        return instance.registerService;
    }

    @Override
    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        this.applicationContext = contex;
    }

}
