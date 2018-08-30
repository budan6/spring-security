package com.efruit.ark.microsvr.user.service.impl;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.PermissionInfo;
import com.efruit.ark.microsvr.user.dao.domain.QueryResult;
import com.efruit.ark.microsvr.user.dao.domain.dto.RoleInfoDto;
import com.efruit.ark.microsvr.user.dao.mapper.RoleInfoDtoMapper;
import com.efruit.ark.microsvr.user.service.PermissionService;
import com.efruit.ark.microsvr.user.dao.domain.dto.PermissionInfoDto;
import com.efruit.ark.microsvr.user.dao.mapper.PermissionInfoDtoMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 2018/8/21.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 查询列表
     *
     * @param permissionInfo
     *   roleid：角色id
     * @return
     */
    @Override
    public ArkCommonResult list(PermissionInfo permissionInfo) {
        logger.info("查询权限列表参数为：" + permissionInfo);
        //查询语句
        StringBuffer sbSql = new StringBuffer();
        try{
            sbSql.append(" select per.* ,createuser.nickname as createbyname,lastmodifyuser.nickname as lastmodifybyname," );
            sbSql.append(" role_info.rolename as rolename" );
            sbSql.append(" from permission_info per left join user_info createuser on per.createbyid = createuser.userid" );
            sbSql.append(" left join user_info lastmodifyuser on per.lastmodifybyid = lastmodifyuser.userid, " );
            sbSql.append(" role_info role_info,user_info user" );
            sbSql.append(" where per.roleid = role_info.roleid" );
            sbSql.append(" and role_info.roleid = user.roleid" );
            if(!StringUtils.isEmpty(permissionInfo.getRoleid())){
                sbSql.append(" and role_info.roleid = '" + permissionInfo.getRoleid() + "'" );
            }

            logger.info("查询权限列表的sql为" + sbSql.toString());
            List<PermissionInfoDto> listPermissionInfoDto = jdbcTemplate.query(sbSql.toString(), new PermissionInfoDtoMapper());
            QueryResult queryResult = new QueryResult();
            queryResult.setCount(listPermissionInfoDto != null && listPermissionInfoDto.size() > 0 ? listPermissionInfoDto.size() : 0 );
            queryResult.setList(listPermissionInfoDto);
            return ArkCommonResult.ok(queryResult);
        }catch (Exception e){
            logger.error("查询权限列表发生异常" ,e);
            return ArkCommonResult.fail("查询权限列表发生异常：" + e.getMessage());
        }finally {
            sbSql = null;
        }
    }

    /**
     * 新增权限
     *
     * @param permissionInfo
     * @return
     */
    @Override
    public ArkCommonResult insert(PermissionInfo permissionInfo) {
        logger.info("新增权限参数为：" + permissionInfo );
        int intLength=0;
        String strValue="";
        String strType="";

        StringBuffer sbSQL = new StringBuffer();
        StringBuffer sbValue = new StringBuffer();
        try{

            //根据角色id,权限url查询是否存在
            sbSQL.append("select 1 from permission_info WHERE roleid = ? and url = ?" );
            Object[] params = new Object[] { permissionInfo.getRoleid(),permissionInfo.getUrl().trim() };

            logger.info("本次执行根据角色id,权限url查询是否存在sql为:" + sbSQL.toString() );

            List<String> listPermission =  jdbcTemplate.queryForList(sbSQL.toString(), params, String.class);
            if(listPermission!=null && listPermission.size() > 0 ){
                return ArkCommonResult.build(802,"角色下已经存在此权限");
            }

            sbSQL = new StringBuffer();

            sbSQL.append(" insert into permission_info (");
            if(!StringUtils.isEmpty(permissionInfo.getPermissionid())){
                sbSQL.append(" permissionid");
                sbValue.append(" ?");
                strValue=strValue+permissionInfo.getPermissionid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(permissionInfo.getName())){
                sbSQL.append(" ,name");
                sbValue.append(" ,?");
                strValue=strValue+permissionInfo.getName()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(permissionInfo.getDescritpion())){
                sbSQL.append(" ,descritpion");
                sbValue.append(" ,?");
                strValue=strValue+permissionInfo.getDescritpion()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(permissionInfo.getUrl())){
                sbSQL.append(" ,url");
                sbValue.append(" ,?");
                strValue=strValue+permissionInfo.getUrl()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(permissionInfo.getRoleid())){
                sbSQL.append(" ,roleid");
                sbValue.append(" ,?");
                strValue=strValue+permissionInfo.getRoleid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(permissionInfo.getPid())){
                sbSQL.append(" ,pid");
                sbValue.append(" ,?");
                strValue=strValue+permissionInfo.getRoleid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(permissionInfo.getCreatebyid())){
                sbSQL.append(" ,createbyid");
                sbValue.append(" ,?");
                strValue=strValue+permissionInfo.getCreatebyid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(permissionInfo.getLastmodifybyid())){
                sbSQL.append(" ,lastmodifybyid");
                sbValue.append(" ,?");
                strValue=strValue+permissionInfo.getLastmodifybyid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            // 声明参数数组
            if(intLength>0){
                strValue=strValue.substring(0, strValue.length()-1);
                strType=strType.substring(0, strType.length()-1);
            }

            sbSQL.append(" )");
            sbSQL.append(" VALUES (");
            sbSQL.append(sbValue);
            sbSQL.append(") ");


            String[] strValues=strValue.split(",");
            String[] strTypes=strType.split(",");

            Object[] objParams=new Object[intLength];
            int[] intTypes=new int[intLength];
            // 循环，获取map中不为空的值，将参数拼装到数组中
            for(int i=0;i<strValues.length;i++){
                objParams[i]=strValues[i];
                intTypes[i]=Integer.parseInt(strTypes[i]);
            }

            logger.debug("本次执行新增权限的sql为:" + sbSQL.toString() );

            // 执行SQL
            jdbcTemplate.update(sbSQL.toString() ,objParams ,intTypes);
            return ArkCommonResult.ok();

        }catch (Exception e){
            logger.error("新增权限发生异常：" ,e);
            return ArkCommonResult.fail("新增权限发生异常:" + e.getMessage());
        }finally {
            sbSQL = null;
            sbValue = null;
            strValue = null;
            strType = null;
        }
    }

    /**
     * 更新权限
     *
     * @param permissionInfo
     * @return
     */
    @Override
    public ArkCommonResult update(PermissionInfo permissionInfo) {
        logger.info("更新权限参数为：" + permissionInfo );
        int intLength=0;
        String strValue="";
        String strType="";
        StringBuffer sbSQL = new StringBuffer();

        try{

            //根据角色id,权限url&!=permissionid查询是否存在
            sbSQL.append("select 1 from permission_info WHERE roleid = ? and url = ? and permissionid != ?" );
            Object[] params = new Object[] {
                    permissionInfo.getRoleid(),
                    permissionInfo.getUrl().trim(),
                    permissionInfo.getPermissionid()
            };

            logger.info("本次执行根据角色id,权限url&!=permissionid查询是否存在sql为:" + sbSQL.toString() );

            List<String> listPermission =  jdbcTemplate.queryForList(sbSQL.toString(), params, String.class);
            if(listPermission!=null && listPermission.size() > 0 ){
                return ArkCommonResult.build(802,"角色下已经存在此权限");
            }

            sbSQL = new StringBuffer();

            sbSQL.append(" UPDATE permission_info SET ");


            if(!StringUtils.isEmpty(permissionInfo.getName())){
                sbSQL.append(" name = ? ,");
                strValue=strValue+permissionInfo.getName()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(permissionInfo.getDescritpion())){
                sbSQL.append(" descritpion = ? ,");
                strValue=strValue+permissionInfo.getDescritpion()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(permissionInfo.getUrl())){
                sbSQL.append(" url = ? , ");
                strValue=strValue+permissionInfo.getUrl()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(permissionInfo.getRoleid())){
                sbSQL.append(" roleid = ? , ");
                strValue=strValue+permissionInfo.getRoleid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(permissionInfo.getPid())){
                sbSQL.append(" pid = ? , ");
                strValue=strValue+permissionInfo.getRoleid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }



            if(!StringUtils.isEmpty(permissionInfo.getLastmodifybyid())){
                sbSQL.append(" lastmodifybyid = ? ,");
                strValue=strValue+permissionInfo.getLastmodifybyid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            sbSQL.deleteCharAt(sbSQL.length()-1);
            sbSQL.append(" WHERE permissionid = ?");
            strValue=strValue+permissionInfo.getPermissionid()+",";
            strType=strType+java.sql.Types.VARCHAR+",";
            intLength++;

            Object[] objParams= strValue.split(",");
            int[] intTypes = new int[intLength];

            String[] strTypes = strType.split(",");
            for (int i = 0 ; i < strTypes.length ;i++  ){
                intTypes[i] = Integer.parseInt(strTypes[i]);
            }

            logger.info("本次执行更新权限的sql为:" + sbSQL.toString() );

            // 执行SQL
            jdbcTemplate.update(sbSQL.toString() ,objParams ,intTypes);
            return ArkCommonResult.ok();
        }catch (Exception e){
            logger.error("更新权限发生异常：" ,e);
            return ArkCommonResult.fail("更新权限发生异常:" + e.getMessage());
        }finally {
            sbSQL = null;
            strValue = null;
            strType = null;
        }
    }

    /**
     * 删除权限
     *
     * @param permissionid 权限id
     * @return
     */
    @Override
    public ArkCommonResult delete(String permissionid) {
        logger.info("删除权限参数为: " + permissionid);
        StringBuffer sbSql = new StringBuffer();
        try{
            sbSql.append("DELETE FROM permission_info WHERE permissionid = ?" );
            Object[] params = new Object[] { permissionid };

            logger.info("本次执行删除权限的sql为:" + sbSql.toString() );

            jdbcTemplate.update( sbSql.toString() ,params,new int [] { java.sql.Types.VARCHAR });
            return ArkCommonResult.ok();
        }catch(Exception e){
            logger.error("删除权限发生异常发生异常\n",e);
            return ArkCommonResult.fail("删除权限发生异常发生异常:" + e.getMessage());
        }finally {
            sbSql = null;
        }
    }

}
