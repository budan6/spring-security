package com.efruit.ark.microsvr.oauth2.component;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by yangyang on 2018/8/20.
 */
@Component
public class Budan6AuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //表单中的用户名
        String userName = authentication.getName();
        //表单中的密码

        String password = DigestUtils.md5Hex((String) authentication.getCredentials());
        // 这里构建来判断用户是否存在和密码是否正确
        LoginAuthUser authUser = (LoginAuthUser)userDetailService.loadUserByUsername(userName);
        if (authUser == null) {
            throw new BadCredentialsException("用户名不存在");
        }

        //将密码进行加盐与数据库中密码进行比较
        String encodePwd = bCryptPasswordEncoder.encode(password);
        //这里判断密码正确与否
        if(!authUser.getPassword().equals(password)){
            throw new BadCredentialsException("密码不正确");
        }

        if(!authUser.isEnabled()){
            throw new BadCredentialsException("用户已禁用");
        }

        Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(authUser, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
