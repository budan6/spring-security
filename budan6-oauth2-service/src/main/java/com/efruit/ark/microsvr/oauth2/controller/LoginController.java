package com.efruit.ark.microsvr.oauth2.controller;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制类
 * 访问oauth2服务
 * http://localhost:8001/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://baidu.com&state=123
 * Created by yangyang on 2018/8/16.
 */
@RestController
@CrossOrigin
public class LoginController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录验证
     * @param request
     * @return
     */
    @RequestMapping(value="login/form",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void login(HttpServletRequest request ) {
        logger.info("登录");
    }



    @RequestMapping("/whoim")
    public Object whoIm() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
