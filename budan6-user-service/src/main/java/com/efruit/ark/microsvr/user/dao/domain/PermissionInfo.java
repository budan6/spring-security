package com.efruit.ark.microsvr.user.dao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 权限信息
 * Created by yangyang on 2018/8/21.
 */
public class PermissionInfo implements Serializable {
    private String permissionid;//权限id
    private String name;//权限名称
    private String descritpion;//权限描述
    private String url;//授权链接
    private String roleid;//角色id
    private String pid;//父节点id
    private String createbyid;//创建人id
    private String lastmodifybyid;//最后修改人id
    //格式化时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp lastmodifydate;//最后修改时间
    //格式化时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createdate;//创建时间

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreatebyid() {
        return createbyid;
    }

    public void setCreatebyid(String createbyid) {
        this.createbyid = createbyid;
    }

    public String getLastmodifybyid() {
        return lastmodifybyid;
    }

    public void setLastmodifybyid(String lastmodifybyid) {
        this.lastmodifybyid = lastmodifybyid;
    }

    public Timestamp getLastmodifydate() {
        return lastmodifydate;
    }

    public void setLastmodifydate(Timestamp lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }
}
