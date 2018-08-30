package com.efruit.ark.microsvr.user.dao.domain.dto;

import com.efruit.ark.microsvr.user.dao.domain.PermissionInfo;

/**
 * 权限信息
 * Created by yangyang on 2018/8/21.
 */
public class PermissionInfoDto extends PermissionInfo {
    private String createbyname;//创建人
    private String lastmodifybyname;//最后修改人
    private String rolename;//角色名称

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getCreatebyname() {
        return createbyname;
    }

    public void setCreatebyname(String createbyname) {
        this.createbyname = createbyname;
    }

    public String getLastmodifybyname() {
        return lastmodifybyname;
    }

    public void setLastmodifybyname(String lastmodifybyname) {
        this.lastmodifybyname = lastmodifybyname;
    }
}
