package com.efruit.ark.microsvr.user.filter;


import com.efruit.ark.microsvr.user.component.JwtUtilService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * token校验
 * Created by yangyang on 2018/8/21.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    final static String AUTHORIZATION_NAME = "Authorization";//请求头参数名称
    final static String AUTHORIZATION_VALUE_PRE = "Budan6 ";//请求头参数前缀

    final static String USERDETAILS_ROLE = "ADMIN ";//初始化用户角色（无实际意义，用于构建UsernamePasswordAuthenticationToken）
    final static String USERDETAILS_PASSWORD = "111111 ";//初始化用户密码（无实际意义，用于构建UsernamePasswordAuthenticationToken）

    private JwtUtilService jwtUtilService;

    public JwtAuthenticationTokenFilter(JwtUtilService jwtUtilService) {
        this.jwtUtilService = jwtUtilService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //获取请求url
        String requestUrl = request.getRequestURI();
        String strAuthorization = request.getHeader(AUTHORIZATION_NAME);
        if (strAuthorization == null || !strAuthorization.startsWith(AUTHORIZATION_VALUE_PRE)) {
            chain.doFilter(request, response);
            return;
        }

        String strToken = strAuthorization.substring(7,strAuthorization.length());

        //检查token有效性
        boolean blnContinueRequst = jwtUtilService.verifyRegisterLink(strToken,requestUrl);

        if (!blnContinueRequst) {
            chain.doFilter(request, response);
            return;
        }

        //设置创建用户id及最后修改id
        Map<String, Object> extraParams = new HashMap<String, Object>();
        extraParams.put("userid", jwtUtilService.genUserid(strToken));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(USERDETAILS_ROLE);
        grantedAuthorities.add(grantedAuthority);
        //构建用户
        UserDetails userDetails = new User(jwtUtilService.genUsername(strToken),USERDETAILS_PASSWORD,grantedAuthorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                request));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        //利用原始的request对象创建自己扩展的request对象并添加自定义参数
        RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
        requestParameterWrapper.addParameters(extraParams);

        chain.doFilter(requestParameterWrapper, response);

    }


}
