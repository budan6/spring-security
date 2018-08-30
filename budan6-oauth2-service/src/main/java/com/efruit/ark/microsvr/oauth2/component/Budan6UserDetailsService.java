package com.efruit.ark.microsvr.oauth2.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录认证服务
 * Created by yangyang on 2018/8/18.
 */
@Component
public class Budan6UserDetailsService implements UserDetailsService {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Budan6UserDetailsService.class);

    @Value("${ark.user.service.url}")
    String strUserServiceURL ;


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders httpHeaders;

    /**
     * 重写security的方法
     * 根据用户名获取登录认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("开始登录认证：" + username);

        //返回用户信息
        LoginAuthUser user = null;

        String strLoginname = null;
        String strPassword = null;
        String strRolename = null;
        Boolean blnIsenable = true;
        try{

            //调用用户服务获取登录认证信息
            String url = strUserServiceURL + "/auth/login/user";
            MultiValueMap<String, Object> mvmRequsetPharm = new LinkedMultiValueMap<String, Object>();
            mvmRequsetPharm.add("loginName", username.trim());

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(mvmRequsetPharm,headers);
            ResponseEntity<String> reResult = restTemplate.postForEntity(url, httpEntity, String.class);

            System.out.println("登录认证结果为：" + reResult.toString());

            //获取用户的登录账号，用户密码，是否启用，角色名称，用户类型
            //请求成功
            if(reResult.getStatusCode().is2xxSuccessful()){
                JSONObject JSONObjectResult = JSON.parseObject(reResult.getBody().toString());
                //获取status
                String status = JSONObjectResult.get("status").toString();
                String msg = JSONObjectResult.get("msg").toString();
                if(!"800".equals(status)){
                    throw new BadCredentialsException(msg);
                }

                //获取用户信息
                JSONObject jsonData = JSON.parseObject(JSONObjectResult.get("data").toString());
                JSONObject jsonDataUser = JSON.parseObject(jsonData.get("user").toString());
                String userid = jsonDataUser.get("userid").toString();

                if(!StringUtils.isEmpty(jsonDataUser.get("isenable").toString())){
                    if("禁用".equals(jsonDataUser.get("isenable").toString().trim())) blnIsenable = false;

                }
                strPassword = jsonDataUser.get("password").toString();

                //获取授权信息
                JSONArray JSONArrayPermissionList = JSON.parseArray(jsonData.get("permissionList").toString());
                String permissionid = null;
                String name = null;
                String permissionUrl = null;

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                //用于存放权限
                for (int i = 0 ;i< JSONArrayPermissionList.size();i++) {
                    JSONObject JSONObjectPermission = JSONArrayPermissionList.getJSONObject(i);
//                    HashMap<String, String> mapPermission = new HashMap<>();
//                    System.out.println(JSONObjectPermission.get("permissionid"));
//                    System.out.println(JSONObjectPermission.get("name"));
//                    System.out.println(JSONObjectPermission.get("url"));
//                    mapPermission.put(JSONObjectPermission.get("url").toString(),JSONObjectPermission.get("name").toString());
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(JSONObjectPermission.get("url").toString());
                    grantedAuthorities.add(grantedAuthority);
                }

                //返回用户对象
                user = new LoginAuthUser(userid,username, strPassword, blnIsenable,true,true, true, grantedAuthorities);

            }else{
                //请求失败
            }


        }catch (Exception e){
            throw new BadCredentialsException("服务发生异常" + e.getMessage());
//            e.printStackTrace();
        }finally {
            strLoginname = null;
            strPassword = null;
            strRolename = null;
            blnIsenable = null;
        }

        return user;
    }

}
