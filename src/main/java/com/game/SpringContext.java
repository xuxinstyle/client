package com.game;

import com.game.base.account.service.AccountService;
import com.game.base.executor.common.CommonExecutorService;
import com.game.connect.service.IConnectService;
import com.game.login.service.ILoginService;
import com.game.register.service.RegisterService;
import com.game.role.player.service.PlayerService;
import com.game.scence.service.ScenceService;
import com.game.user.equip.service.EquipService;
import com.game.user.equipupgrade.service.EquipUpgradeService;
import com.game.user.item.service.ItemService;
import com.game.user.strenequip.service.StrenEquipService;
import com.resource.core.StorageManager;
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
    private IConnectService connectService;
    @Autowired
    private ILoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ScenceService scenceService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private EquipService equipService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CommonExecutorService commonExecutorService;
    @Autowired
    private StrenEquipService strenEquipService;
    @Autowired
    private EquipUpgradeService equipUpgradeService;

    public static EquipUpgradeService getEquipUpgradeService(){
        return instance.equipUpgradeService;
    }

    public static StrenEquipService getStrenEquipService(){
        return instance.strenEquipService;
    }

    public static CommonExecutorService getCommonExecutorService(){
        return instance.commonExecutorService;
    }

    public static PlayerService getPlayerService(){
        return instance.playerService;
    }

    public static EquipService getEquipService(){
        return instance.equipService;
    }

    public static ItemService getItemService(){
        return instance.itemService;
    }

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
