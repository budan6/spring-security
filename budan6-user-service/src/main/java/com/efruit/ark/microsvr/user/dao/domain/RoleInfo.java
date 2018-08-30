package com.efruit.ark.microsvr.user.dao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 角色信息
 * Created by yangyang on 2018/8/16.
 */
public class RoleInfo implements Serializable {

    private String roleid;//角色id
    private String rolename;//角色名称
    private String isenable;//是否启用（启用/禁用）
    //格式化时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createdate;//创建时间
    private String createbyid;//创建人
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp lastmodifydate;//最后修改时间
    private String lastmodifybyid;//最后修改人

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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
