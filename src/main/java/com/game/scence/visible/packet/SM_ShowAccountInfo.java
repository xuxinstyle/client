package com.game.scence.visible.packet;

import com.game.base.attribute.Attribute;
import com.game.scence.visible.model.Position;
import java.util.List;

/**
 * @Author：xuxin
 * @Date: 2019/6/6 10:11
 */
public class SM_ShowAccountInfo {

    /**
     * 账号Id
     */
    private String accountId;
    /**
     * 职业
     */
    private int career;
    /**
     * 等级
     */
    private int level;

    /**
     * 角色名
     */
    private String playerName;
    /*
     * 位置
     *
     */
    private Position position;

    /**
     * 属性
     */
    private List<Attribute> attributeList;

    /**
     * 当前血量
     */
    private long currHp;

    /**
     * 当前mp
     */
    private long currMp;

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public long getCurrHp() {
        return currHp;
    }

    public void setCurrHp(long currHp) {
        this.currHp = currHp;
    }

    public long getCurrMp() {
        return currMp;
    }

    public void setCurrMp(long currMp) {
        this.currMp = currMp;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
