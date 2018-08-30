package com.efruit.ark.microsvr.oauth2.hander;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.efruit.ark.microsvr.oauth2.component.JwtUtilService;
import com.efruit.ark.microsvr.oauth2.component.LoginAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理登录成功
 * Created by yangyang on 2018/8/20.
 */
@Component
public class Budan6AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Autowired
    JwtUtilService jwtUtilService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("处理登录成功 onAuthenticationSuccess");
        String token = jwtUtilService.genToken((LoginAuthUser) authentication.getPrincipal());
        Map<String,String> map=new HashMap<>();
        map.put("status", "800");
        map.put("msg", "登录成功");
        map.put("token", token);
        response.setContentType("application/json;charset=UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        response.getWriter().write(jsonObject.toString());
        System.out.println("生成token为：" + map.toString());
        //this.saveLoginInfo(request, authentication);
    }

}