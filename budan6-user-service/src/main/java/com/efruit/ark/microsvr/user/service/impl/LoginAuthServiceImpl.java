package com.efruit.ark.microsvr.user.service.impl;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.QueryResult;
import com.efruit.ark.microsvr.user.dao.domain.UserInfo;
import com.efruit.ark.microsvr.user.dao.domain.dto.PermissionInfoDto;
import com.efruit.ark.microsvr.user.dao.domain.dto.UserInfoDto;
import com.efruit.ark.microsvr.user.dao.mapper.PermissionInfoDtoMapper;
import com.efruit.ark.microsvr.user.dao.mapper.UserAuthMapper;
import com.efruit.ark.microsvr.user.service.LoginAuthService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 2018/8/23.
 */
@Service
public class LoginAuthServiceImpl implements LoginAuthService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAuthService.class);
    /**
     * 登录认证
     * 1 根据登录账号获取用户信息
     * @param loginName ：登录账号
     * @return
     */
    @Override
    public Map<String, Object> auth(String loginName) {
        //返回信息
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("status","800");
        mapResult.put("msg","登录认证成功");
        StringBuffer sbSql = new StringBuffer();
        try{
            sbSql.append(" select user.userid,user.loginname,user.password,user.isenable,user.openid,"  );
            sbSql.append(" role_info.rolename as rolename,user.usertype"  );
            sbSql.append(" from user_info user ,role_info role_info "  );
            sbSql.append(" where user.roleid = role_info.roleid ");
            sbSql.append(" and user.loginname = ? or user.openid = ? ");

            Object[] params = new Object[] { loginName,loginName };

            logger.info("查询登录的sql为" + sbSql.toString());
            List<UserInfoDto> listUser = jdbcTemplate.query(sbSql.toString(), params, new UserAuthMapper());
            if(listUser != null && listUser.size() > 0 ){
                //返回的用户信息
                UserInfo userInfo = listUser.get(0);
                //根据用户id获取权限信息
                sbSql = new StringBuffer();
                sbSql.append(" select per.* ,'' as createbyname,'' as lastmodifybyname," );
                sbSql.append(" role_info.rolename as rolename" );
                sbSql.append(" from permission_info per, " );
                sbSql.append(" role_info role_info,user_info user" );
                sbSql.append(" where per.roleid = role_info.roleid" );
                sbSql.append(" and role_info.roleid = user.roleid" );
                sbSql.append(" and user.userid = ? " );

                logger.info("根据用户id获取权限信息的sql为" + sbSql.toString());
                params = new Object[] { userInfo.getUserid() };
                List<PermissionInfoDto> listPermissionInfoDto = jdbcTemplate.query(sbSql.toString(),params, new PermissionInfoDtoMapper());
                if(listPermissionInfoDto != null && listPermissionInfoDto.size() > 0 ){
                    Map mapDataDetail = new HashMap();
                    mapDataDetail.put("user",userInfo);
                    mapDataDetail.put("permissionList",listPermissionInfoDto);

                    mapResult.put("status","800");
                    mapResult.put("msg","获取登录认证成功");
                    mapResult.put("data",mapDataDetail);
                    return mapResult;
                }else{
                    mapResult.put("status","802");
                    mapResult.put("msg","没有任何访问权限");
                    return mapResult;
                }

            }else{
                mapResult.put("status","801");
                mapResult.put("msg","用户不存在");
                return mapResult;
            }


        }catch(Exception e){
            logger.error("根据登录账号获取用户信息发生异常" ,e);
            mapResult.put("status","819");
            mapResult.put("msg","根据登录账号获取用户信息发生异常：" + e.getMessage());
            return mapResult;
        }finally {
            sbSql = null;
        }
    }
}
