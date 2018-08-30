package com.efruit.ark.microsvr.user.dao.mapper;

import com.efruit.ark.microsvr.user.dao.domain.dto.UserInfoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangyang on 2018/8/16.
 */
public class UserInfoDtoMapper implements RowMapper<UserInfoDto> {

    @Override
    public UserInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setUserid(resultSet.getString("userid"));//用户id
        userInfo.setLoginname(resultSet.getString("loginname"));//登录账号
        userInfo.setNickname(resultSet.getString("nickname"));//用户名
        userInfo.setPassword(resultSet.getString("password"));//密码
        userInfo.setWechatid(resultSet.getString("wechatid"));//微信号
        userInfo.setWechatheadimg(resultSet.getString("wechatheadimg"));//微信头像
        userInfo.setOpenid(resultSet.getString("openid"));//微信唯一标示
        userInfo.setSex(resultSet.getString("sex"));//性别(0女1男)
        userInfo.setIsenable(resultSet.getString("isenable"));//是否启用(1启用0禁用)
        userInfo.setRegion(resultSet.getString("region"));//地区
        userInfo.setPhone(resultSet.getString("phone"));//地区
        userInfo.setRoleid(resultSet.getString("roleid"));//角色id
        userInfo.setRoleName(resultSet.getString("rolename"));//角色名称
        userInfo.setCreatedate(resultSet.getTimestamp("createdate"));//创建时间
        userInfo.setCreatebyid(resultSet.getString("createbyid"));//创建人id
        userInfo.setCreatebyname(resultSet.getString("createbyname"));//创建人
        userInfo.setLastmodifydate(resultSet.getTimestamp("lastmodifydate"));//最后修改时间
        userInfo.setLastmodifybyid(resultSet.getString("lastmodifybyid"));//最后修改人id
        userInfo.setLastmodifybyname(resultSet.getString("lastmodifybyname"));//最后修改人
        userInfo.setUsertype(resultSet.getString("usertype"));//用户类型(manage/wechat)
        return userInfo;
    }
}

