package com.efruit.ark.microsvr.user.config;

import com.efruit.ark.microsvr.user.component.JwtAuthenticationEntryPoint;
import com.efruit.ark.microsvr.user.component.JwtUtilService;
import com.efruit.ark.microsvr.user.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置
 * Created by yangyang on 2018/8/18.
 */
@Order(3)
@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    //未授权响应处理类
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    JwtUtilService jwtUtilService;

    @Override
    @Bean
    //授权管理器
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // 使用JWT，不需要csrf
                .csrf().disable()
                //未授权处理
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                // 基于token，不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/login/user").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //创建过滤器，过滤jwt请求
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter(jwtUtilService);
        httpSecurity    //把过滤器添加到安全策略里面去
        .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }


}