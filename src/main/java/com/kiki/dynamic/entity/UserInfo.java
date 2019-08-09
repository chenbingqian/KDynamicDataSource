/*
 * @Title: UserInfo.java
 */
package com.kiki.dynamic.entity;

/**
 * 实体
 *
 * @ClassName: UserInfo
 * @author kiki
 * @date 2019年8月9日
 * @version: V1.0
 */
public class UserInfo {

    private Integer uid;
    private String uname;
    private String mail;
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


}
