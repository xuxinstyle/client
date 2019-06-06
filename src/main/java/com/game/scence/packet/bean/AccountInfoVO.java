package com.game.scence.packet.bean;

/**
 * @Author：xuxin
 * @Date: 2019/6/6 10:14
 */
public class AccountInfoVO {
    /**
     * 账号Id
     */
    private String accountId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 职业
     */
    private String career;
    /**
     * 等级
     */
    private int level;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
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
