package com.game.scence.packet;

import com.game.scence.packet.bean.PlayerVO;

import java.util.List;

/**
 * 响应查看场景中的玩家
 * @Author：xuxin
 * @Date: 2019/6/6 10:02
 */
public class SM_ShowAllAccountInfo {
    private List<PlayerVO> playerVOList;

    public List<PlayerVO> getPlayerVOList() {
        return playerVOList;
    }

    public void setPlayerVOList(List<PlayerVO> playerVOList) {
        this.playerVOList = playerVOList;
    }
}
