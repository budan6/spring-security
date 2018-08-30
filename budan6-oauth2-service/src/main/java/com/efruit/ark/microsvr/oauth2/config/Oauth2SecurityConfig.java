package com.efruit.ark.microsvr.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * spring Security配置
 * Created by yangyang on 2018/8/18.
 */
@Order(3)
@Configuration
@EnableWebSecurity
public class Oauth2SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;




    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin().loginProcessingUrl("/login/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                //这就表示/whoim的这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
                .antMatchers("/whoim").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/manage/*/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/manage/*/*").hasRole("USER")
                //除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and().logout().permitAll() //注销行为任意访问
                .and()
                .csrf().disable();
        // 基于token，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * 用户名密码认证方法
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }


}