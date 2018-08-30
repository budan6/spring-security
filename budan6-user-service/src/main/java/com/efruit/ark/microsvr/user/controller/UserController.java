package com.efruit.ark.microsvr.user.controller;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.UserInfo;
import com.efruit.ark.microsvr.user.filter.RequestParameterWrapper;
import com.efruit.ark.microsvr.user.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户信息控制类
 * Created by yangyang on 2018/8/16.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/manage/user")
public class UserController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    /**
     * 获取用户列表
     * @param userInfo
     *  wechatid:微信账号
     *  loginname:登录账号
     *  sex:性别
     *  isenable:是否启用
     *  usertype:用户类型
     * @return
     */
    @RequestMapping(value="list",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ArkCommonResult list(UserInfo userInfo) {
        return  userService.list(userInfo);
    }


    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    @RequestMapping(value="insert",method = RequestMethod.POST)
    public ArkCommonResult insert(UserInfo userInfo,RequestParameterWrapper requestParameterWrapper ) {
        //设置用户id，创建人，最后修改人,是否启用
        userInfo.setUserid(UUID.randomUUID().toString().replaceAll("-", ""));
        userInfo.setCreatebyid(requestParameterWrapper.getParameter("userid"));
        userInfo.setLastmodifybyid(requestParameterWrapper.getParameter("userid"));
        userInfo.setIsenable("启用");
        //密码进行md5加密
        userInfo.setPassword(DigestUtils.md5Hex(userInfo.getPassword()));
        return userService.insert(userInfo);
    }


    /**
     * 修改用户状态
     * @param userInfo
     * @return
     */
    @RequestMapping(value="update",method = RequestMethod.POST)
    public ArkCommonResult update(UserInfo userInfo,RequestParameterWrapper requestParameterWrapper) {
        userInfo.setLastmodifybyid(requestParameterWrapper.getParameter("userid"));
        if(!StringUtils.isEmpty(userInfo.getPassword())){
            //密码进行md5加密
            userInfo.setPassword(DigestUtils.md5Hex(userInfo.getPassword()));
        }
        return userService.update(userInfo);
    }

    /**
     * 检查用户名是否存在（新增前）
     * @param userInfo
     * @return
     */
    @RequestMapping(value="checkLoginname",method = RequestMethod.POST)
    public ArkCommonResult check(UserInfo userInfo) {
        return userService.check(userInfo);
    }


    /**
     * 根据用户id获取用户详细信息
     * @param
     * @return
     */
    @RequestMapping(value="getDetail",method = RequestMethod.POST)
    public ArkCommonResult getDetail(RequestParameterWrapper requestParameterWrapper) {
        return userService.getDetail(requestParameterWrapper.getParameter("userid"));
    }


}
