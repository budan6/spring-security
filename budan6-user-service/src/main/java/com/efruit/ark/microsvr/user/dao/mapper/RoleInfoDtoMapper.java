package com.efruit.ark.microsvr.user.dao.mapper;

import com.efruit.ark.microsvr.user.dao.domain.dto.RoleInfoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangyang on 2018/8/16.
 */
public class RoleInfoDtoMapper implements RowMapper<RoleInfoDto> {

    @Override
    public RoleInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
        RoleInfoDto roleInfoDto = new RoleInfoDto();
        roleInfoDto.setRoleid(resultSet.getString("roleid"));//角色id
        roleInfoDto.setIsenable(resultSet.getString("isenable"));//角色id
        roleInfoDto.setRolename(resultSet.getString("rolename"));//角色名称
        roleInfoDto.setCreatedate(resultSet.getTimestamp("createdate"));//创建时间
        roleInfoDto.setCreatebyid(resultSet.getString("createbyid"));//创建人id
        roleInfoDto.setCreatebyname(resultSet.getString("createbyname"));//创建人
        roleInfoDto.setLastmodifydate(resultSet.getTimestamp("lastmodifydate"));//最后修改时间
        roleInfoDto.setLastmodifybyid(resultSet.getString("lastmodifybyid"));//最后修改人id
        roleInfoDto.setLastmodifybyname(resultSet.getString("lastmodifybyname"));//最后修改人
        return roleInfoDto;
    }
}
