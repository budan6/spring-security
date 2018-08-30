package com.efruit.ark.microsvr.user.dao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户信息
 * Created by yangyang on 2018/8/16.
 */
public class UserInfo implements Serializable {

    private String userid;//用户id
    private String loginname;//登录账号
    private String nickname;//用户名
    private String password;//密码
    private String wechatid;//微信号
    private String wechatheadimg;//微信头像
    private String openid;//微信唯一标示
    private String sex;//性别(女/男)
    private String isenable ;//是否启用(启用/禁用)
    private String region;//地区
    private String roleid;//角色id
    private String phone;//手机号码
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createdate;//创建时间
    private String createbyid;//创建人id
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp lastmodifydate;//最后修改时间
    private String lastmodifybyid;//最后修改人id
    private String usertype;//用户类型(manage/wechat)

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }

    public String getWechatheadimg() {
        return wechatheadimg;
    }

    public void setWechatheadimg(String wechatheadimg) {
        this.wechatheadimg = wechatheadimg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public String getCreatebyid() {
        return createbyid;
    }

    public void setCreatebyid(String createbyid) {
        this.createbyid = createbyid;
    }

    public Timestamp getLastmodifydate() {
        return lastmodifydate;
    }

    public void setLastmodifydate(Timestamp lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public String getLastmodifybyid() {
        return lastmodifybyid;
    }

    public void setLastmodifybyid(String lastmodifybyid) {
        this.lastmodifybyid = lastmodifybyid;
    }
}
