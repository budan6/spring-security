package com.efruit.ark.microsvr.user.dao.mapper;

import com.efruit.ark.microsvr.user.dao.domain.dto.PermissionInfoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangyang on 2018/8/21.
 */
public class PermissionInfoDtoMapper implements RowMapper<PermissionInfoDto> {
    @Override
    public PermissionInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
        PermissionInfoDto permissionInfoDto = new PermissionInfoDto();
        permissionInfoDto.setRoleid(resultSet.getString("roleid"));//角色id
        permissionInfoDto.setRolename(resultSet.getString("rolename"));//角色名称
        permissionInfoDto.setCreatedate(resultSet.getTimestamp("createdate"));//创建时间
        permissionInfoDto.setCreatebyid(resultSet.getString("createbyid"));//创建人id
        permissionInfoDto.setCreatebyname(resultSet.getString("createbyname"));//创建人
        permissionInfoDto.setLastmodifydate(resultSet.getTimestamp("lastmodifydate"));//最后修改时间
        permissionInfoDto.setLastmodifybyid(resultSet.getString("lastmodifybyid"));//最后修改人id
        permissionInfoDto.setLastmodifybyname(resultSet.getString("lastmodifybyname"));//最后修改人
        permissionInfoDto.setPermissionid(resultSet.getString("permissionid"));//权限id
        permissionInfoDto.setUrl(resultSet.getString("url"));//授权链接
        permissionInfoDto.setDescritpion(resultSet.getString("descritpion"));//权限描述
        permissionInfoDto.setPid(resultSet.getString("pid"));//父节点id
        permissionInfoDto.setName(resultSet.getString("name"));//权限名称
        return permissionInfoDto;
    }
}