package com.efruit.ark.microsvr.user.controller;

import com.efruit.ark.microsvr.user.service.LoginAuthService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 登录认证控制类
 * Created by yangyang on 2018/8/16.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/auth/login")
public class LoginAuthController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAuthController.class);


    @Autowired
    LoginAuthService loginAuthService;

    /**
     * 登录
     * 根据登录账号获取用户信息
     * @param loginName:登录账号
     * @return
     * {"status":800,
     * "msg":"OK",
     * "data":{
     *      "user":{
     *          "userid":"0283ad3dc6fd41e5bb26b1fb7df77e9e",
     *          "loginname":"yangyang@efruitpro.com",
     *          "nickname":"yangyang",
     *          "password":"96e79218965eb72c92a549dd5a330112",
     *          "wechatid":null,
     *          "wechatheadimg":null,
     *          "openid":"openid1",
     *          "sex":"男",
     *          "isenable":"启用",
     *          "region":null,
     *          "roleid":null,
     *          "phone":null,
     *          "createdate":null,
     *          "createbyid":null,
     *          "lastmodifydate":null,
     *          "lastmodifybyid":null,
     *          "usertype":null,
     *          "roleName":"超级管理员",
     *          "createbyname":null,
     *          "lastmodifybyname":null
     *      },
     *      "permissionList":[
     *          {"permissionid":"a4f9f503532241b79ce5df878f5f613d",
     *          "name":"测试权限名称(修改)",
     *          "descritpion":"我是测试的数据(修改)",
     *          "url":"/test/1/2",
     *          "roleid":"15288b3baae54ca29af5c78a1dfefdc3",
     *          "pid":null,
     *          "createbyid":"ark",
     *          "lastmodifybyid":"ark",
     *          "lastmodifydate":"2018-08-22 19:43:21",
     *          "createdate":"2018-08-22 19:41:26",
     *          "createbyname":null,
     *          "lastmodifybyname":null,
     *          "rolename":"超级管理员"
     *          },
     *          {"permissionid":"a4f9f503532241b79ce5df878f5f613d",
     *          "name":"测试权限名称(修改)",
     *          "descritpion":"我是测试的数据(修改)",
     *          "url":"/test/1/2",
     *          "roleid":"15288b3baae54ca29af5c78a1dfefdc3",
     *          "pid":null,
     *          "createbyid":"ark",
     *          "lastmodifybyid":"ark",
     *          "lastmodifydate":"2018-08-22 19:43:21",
     *          "createdate":"2018-08-22 19:41:26",
     *          "createbyname":null,
     *          "lastmodifybyname":null,
     *          "rolename":"超级管理员"
     *          }
     *      ],
     *       "count":2
     *       }
     *}
     */
    @RequestMapping(value="user",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> auth(String loginName) {
        return loginAuthService.auth(loginName);
    }

}
