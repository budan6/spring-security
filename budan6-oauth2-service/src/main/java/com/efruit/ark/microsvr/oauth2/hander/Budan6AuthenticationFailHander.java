package com.efruit.ark.microsvr.oauth2.hander;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录失败
 * Created by yangyang on 2018/8/20.
 */
@Component
public class Budan6AuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Budan6AuthenticationFailHander.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        logger.info("登录失败 ArkAuthenticationFailHander");
        Map<String,String> map=new HashMap<>();
        map.put("status", "201");
        map.put("msg", exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        JSONObject jsonObject =  JSONObject.parseObject(JSON.toJSONString(map));
        response.getWriter().write(jsonObject.toString());
        System.out.println("登录失败：" + jsonObject.toString());

    }
}

