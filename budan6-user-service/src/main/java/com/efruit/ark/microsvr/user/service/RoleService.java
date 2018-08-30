package com.efruit.ark.microsvr.user.service;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.RoleInfo;

import java.util.Map;

/**
 * 角色服务接口
 * Created by yangyang on 2018/8/16.
 */
public interface RoleService {

    /**
     * 查询列表
     * @param roleInfo
     * @return
     */
    ArkCommonResult list(RoleInfo roleInfo);

    /**
     * 新增角色
     * @param roleInfo
     * @return
     */
    ArkCommonResult insert(RoleInfo roleInfo);

    /**
     * 更新角色
     * @param roleInfo
     * @return
     */
    ArkCommonResult update(RoleInfo roleInfo);

    /**
     * 检查角色名称是否存在
     * @param roleInfo
     * @return
     */
    ArkCommonResult checkRolename(RoleInfo roleInfo);
}

