package com.game.role.equip.packet;

import com.game.role.equip.packet.bean.EquipmentVO;

import java.util.Map;

/**
 * @Author：xuxin
 * @Date: 2019/6/18 11:34
 */
public class SM_ShowEquipInfo {
    /**
     * 角色名
     */
    private String playerName;
    /**
     * 职业
     */
    private int job;
    /**
     * 角色唯一id
     */
    private long playerId;
    /**
     * 玩家装备栏信息<位置, 装备信息>
     */
    private Map<Integer, EquipmentVO> positionEquipment;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Map<Integer, EquipmentVO> getPositionEquipment() {
        return positionEquipment;
    }

    public void setPositionEquipment(Map<Integer, EquipmentVO> positionEquipment) {
        this.positionEquipment = positionEquipment;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }
}
