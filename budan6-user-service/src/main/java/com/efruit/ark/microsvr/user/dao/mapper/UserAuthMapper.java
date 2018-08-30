package com.efruit.ark.microsvr.user.dao.mapper;

import com.efruit.ark.microsvr.user.dao.domain.dto.UserInfoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户认证
 * Created by yangyang on 2018/8/16.
 */
public class UserAuthMapper implements RowMapper<UserInfoDto> {

    @Override
    public UserInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setUserid(resultSet.getString("userid"));//用户id
        userInfo.setLoginname(resultSet.getString("loginname"));//登录账号
        userInfo.setPassword(resultSet.getString("password"));//密码
        userInfo.setUsertype(resultSet.getString("usertype"));//用户类型
        userInfo.setOpenid(resultSet.getString("openid"));//微信唯一标示
        userInfo.setIsenable(resultSet.getString("isenable"));//是否启用(1启用0禁用)
        userInfo.setRoleName(resultSet.getString("rolename"));//角色名称
        return userInfo;
    }
}

