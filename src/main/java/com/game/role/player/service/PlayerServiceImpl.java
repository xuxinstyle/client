package com.game.role.player.service;

import org.springframework.stereotype.Component;

/**
 * @Author：xuxin
 * @Date: 2019/6/17 17:50
 */
@Component
public class PlayerServiceImpl implements PlayerService {
    @Override
    public void playerUpLevel(String playerName,int proLevel, int afterLevel) {
        System.out.println("恭喜["+playerName+"]从["+proLevel+"]级"+"升级到["+afterLevel+"]级");
    }
}
