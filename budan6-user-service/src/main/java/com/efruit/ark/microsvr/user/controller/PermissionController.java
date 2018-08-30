package com.efruit.ark.microsvr.user.controller;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.PermissionInfo;
import com.efruit.ark.microsvr.user.filter.RequestParameterWrapper;
import com.efruit.ark.microsvr.user.service.PermissionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 权限控制类
 * Created by yangyang on 2018/8/16.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/manage/permission")
public class PermissionController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PermissionController.class);


    @Autowired
    PermissionService permissionService;

    /**
     * 获取权限列表
     * @param permissionInfo
     * @return
     */
    @RequestMapping(value="list",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ArkCommonResult list(PermissionInfo permissionInfo ) {
        return  permissionService.list(permissionInfo);
    }


    /**
     * 新增权限
     * @param permissionInfo
     * @return
     */
    @RequestMapping(value="insert",method = RequestMethod.POST)
    public ArkCommonResult insert(PermissionInfo permissionInfo,RequestParameterWrapper requestParameterWrapper) {
        permissionInfo.setPermissionid(UUID.randomUUID().toString().replaceAll("-", ""));
        permissionInfo.setCreatebyid(requestParameterWrapper.getParameter("userid"));
        permissionInfo.setLastmodifybyid(requestParameterWrapper.getParameter("userid"));
        return permissionService.insert(permissionInfo);
    }


    /**
     * 修改权限
     * @param permissionInfo
     * @return
     */
    @RequestMapping(value="update",method = RequestMethod.POST)
    public ArkCommonResult update(PermissionInfo permissionInfo,RequestParameterWrapper requestParameterWrapper) {
        permissionInfo.setLastmodifybyid(requestParameterWrapper.getParameter("userid"));
        return permissionService.update(permissionInfo);
    }

    /**
     * 删除权限
     * @param permissionid :权限id
     * @return
     */
    @RequestMapping(value="delete",method = RequestMethod.POST)
    public ArkCommonResult delete(String permissionid) {
        return permissionService.delete(permissionid);
    }


}
