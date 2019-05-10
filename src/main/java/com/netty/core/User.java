package com.netty.core;

import org.msgpack.annotation.Message;

import java.util.Date;
import java.util.List;

@Message
public class User {
    private Integer pId;
    private String pName;
    private Date birthday;
    private CM_Test1 cm;
    private Boolean isMarry;
    private List<Integer> list;

    public Boolean getMarry() {
        return isMarry;
    }

    public void setMarry(Boolean marry) {
        isMarry = marry;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public CM_Test1 getCm() {
        return cm;
    }

    public void setCm(CM_Test1 cm) {
        this.cm = cm;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Boolean getIsMarry() {
        return isMarry;
    }

    public void setIsMarry(Boolean isMarry) {
        this.isMarry = isMarry;
    }

    @Override
    public String toString() {
        return "MyPack{" +
                "birthday=" + birthday +
                ", pId=" + pId +
                ", pName='" + pName + '\'' +
                ", isMarry=" + isMarry +
                '}';
    }
}
