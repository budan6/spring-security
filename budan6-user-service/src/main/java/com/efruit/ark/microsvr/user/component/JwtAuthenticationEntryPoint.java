package com.efruit.ark.microsvr.user.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT异常处理类
 * Created by yangyang on 2018/8/24.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        Map<String,String> map=new HashMap<>();
        map.put("status", "401");
        map.put("msg", "无效的token，请检查token，或者重新登录");
        response.setContentType("application/json;charset=UTF-8");
        JSONObject jsonObject =  JSONObject.parseObject(JSON.toJSONString(map));
        response.getWriter().write(jsonObject.toString());
        System.out.println("不能访问，没权限:" + jsonObject.toString());
    }

}
