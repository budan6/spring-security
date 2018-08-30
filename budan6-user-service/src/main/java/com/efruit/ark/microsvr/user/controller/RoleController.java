package com.efruit.ark.microsvr.user.controller;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.RoleInfo;
import com.efruit.ark.microsvr.user.dao.domain.UserInfo;
import com.efruit.ark.microsvr.user.filter.RequestParameterWrapper;
import com.efruit.ark.microsvr.user.service.RoleService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 角色信息控制类
 * Created by yangyang on 2018/8/16.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/manage/role")
public class RoleController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    RoleService roleService;

    /**
     * 获取角色列表
     * @param roleInfo
     * @return
     */
    @RequestMapping(value="list",method = RequestMethod.POST)
    public ArkCommonResult list(RoleInfo roleInfo) {
        return  roleService.list(roleInfo);
    }


    /**
     * 新增角色
     * @param roleInfo
     * @return
     */
    @RequestMapping(value="insert",method = RequestMethod.POST)
    public ArkCommonResult insert(RoleInfo roleInfo,RequestParameterWrapper requestParameterWrapper) {
        roleInfo.setLastmodifybyid(requestParameterWrapper.getParameter("userid"));
        roleInfo.setCreatebyid(requestParameterWrapper.getParameter("userid"));
        roleInfo.setRoleid(UUID.randomUUID().toString().replaceAll("-", ""));
        return  roleService.insert(roleInfo);
    }


    /**
     * 修改角色
     * @param roleInfo
     * @return
     */
    @RequestMapping(value="update",method = RequestMethod.POST)
    public ArkCommonResult update(RoleInfo roleInfo,RequestParameterWrapper requestParameterWrapper) {
        roleInfo.setLastmodifybyid(requestParameterWrapper.getParameter("userid"));
        return roleService.update(roleInfo);
    }


    /**
     * 检查角色名称是否存在（新增前）
     * @param roleInfo
     * @return
     */
    @RequestMapping(value="checkRolename",method = RequestMethod.POST)
    public ArkCommonResult checkRolename(RoleInfo roleInfo) {
        return roleService.checkRolename(roleInfo);
    }


}
