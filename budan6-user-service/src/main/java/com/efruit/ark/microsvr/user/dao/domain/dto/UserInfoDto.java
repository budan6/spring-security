package com.efruit.ark.microsvr.user.dao.domain.dto;

import com.efruit.ark.microsvr.user.dao.domain.UserInfo;

/**
 * 用户信息
 * Created by yangyang on 2018/8/16.
 */
public class UserInfoDto extends UserInfo {
    private String roleName;//角色名称
    private String createbyname;//创建人
    private String lastmodifybyname;//最后修改人


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
