package com.efruit.ark.microsvr.user.dao.domain.dto;

import com.efruit.ark.microsvr.user.dao.domain.RoleInfo;

/**
 * 角色信息
 * Created by yangyang on 2018/8/16.
 */
public class RoleInfoDto extends RoleInfo {
    private String createbyname;//创建人
    private String lastmodifybyname;//最后修改人


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
