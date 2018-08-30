package com.efruit.ark.microsvr.user.service;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.PermissionInfo;
import com.efruit.ark.microsvr.user.dao.domain.dto.PermissionInfoDto;

import java.util.List;
import java.util.Map;

/**
 * 权限信息服务接口
 * Created by yangyang on 2018/8/21.
 */
public interface PermissionService {


    /**
     * 查询列表
     * @param permissionInfo
     * @return
     */
    ArkCommonResult list(PermissionInfo permissionInfo);

    /**
     * 新增权限
     * @param permissionInfo
     * @return
     */
    ArkCommonResult insert(PermissionInfo permissionInfo);

    /**
     * 更新权限
     * @param permissionInfo
     * @return
     */
    ArkCommonResult update(PermissionInfo permissionInfo);


    /**
     * 删除权限
     * @param permissionid 权限id
     * @return
     */
    ArkCommonResult delete(String permissionid);


}
