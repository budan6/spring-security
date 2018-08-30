package com.efruit.ark.microsvr.user.service.impl;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.RoleInfo;
import com.efruit.ark.microsvr.user.dao.domain.UserInfo;
import com.efruit.ark.microsvr.user.dao.mapper.RoleInfoDtoMapper;
import com.efruit.ark.microsvr.user.service.RoleService;
import com.efruit.ark.microsvr.user.dao.domain.QueryResult;
import com.efruit.ark.microsvr.user.dao.domain.dto.RoleInfoDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 角色服务实现类
 * Created by yangyang on 2018/8/16.
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 查询角色列表
     *
     * @param roleInfo
     * @return
     */
    @Override
    public ArkCommonResult list(RoleInfo roleInfo) {
        logger.info("查询角色列表参数为：" + roleInfo);
        //查询语句
        StringBuffer sbSql = new StringBuffer();
        //参数类型
        Object[] params = null;
        int[] types = null;
        try{
            sbSql.append(" select role_info.roleid,role_info.rolename,role_info.isenable," );
            sbSql.append(" role_info.createdate,role_info.createbyid,"  );
            sbSql.append(" role_info.lastmodifydate,role_info.lastmodifybyid,createuser.nickname as createbyname,"  );
            sbSql.append(" lastmodifyuser.nickname as lastmodifybyname"  );
            sbSql.append(" from role_info role_info left join user_info createuser on role_info.createbyid = createuser.userid"  );
            sbSql.append(" left join user_info lastmodifyuser on role_info.lastmodifybyid = lastmodifyuser.userid "  );
            sbSql.append(" where 1 = 1 "  );

            if(!StringUtils.isEmpty(roleInfo.getRolename()))
                sbSql.append(" AND INSTR(ROLE_INFO.ROLENAME ,'" + roleInfo.getRolename().trim() + "')>0");
            if(!StringUtils.isEmpty(roleInfo.getIsenable()))
                sbSql.append(" AND role_info.isenable = '" + roleInfo.getIsenable() + "'");

            logger.debug("本次执行查询角色列表的sql为:" + sbSql.toString() );

            List<RoleInfoDto> listRole = jdbcTemplate.query(sbSql.toString(), new RoleInfoDtoMapper());
            QueryResult queryResult = new QueryResult();
            queryResult.setCount(listRole != null && listRole.size() > 0 ? listRole.size() : 0 );
            queryResult.setList(listRole);
            return ArkCommonResult.ok(queryResult);
        }catch (Exception e){
            logger.error("查询角色列表发生异常" ,e);
            return ArkCommonResult.fail("查询角色列表发生异常：" + e.getMessage());
        }finally {
            sbSql = null;
        }
    }

    /**
     * 新增角色
     *
     * @param roleInfo
     * @return
     */
    @Override
    public ArkCommonResult insert(RoleInfo roleInfo) {
        logger.info("新增角色参数为：" + roleInfo );
        int intLength=0;
        String strValue="";
        String strType="";

        StringBuffer sbSQL = new StringBuffer();
        StringBuffer sbValue = new StringBuffer();
        try{
            sbSQL.append(" insert into role_info (");
            if(!StringUtils.isEmpty(roleInfo.getRoleid())){
                sbSQL.append(" roleid");
                sbValue.append(" ?");
                strValue=strValue+roleInfo.getRoleid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(roleInfo.getRolename())){
                sbSQL.append(" ,rolename");
                sbValue.append(" ,?");
                strValue=strValue+roleInfo.getRolename()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(roleInfo.getIsenable())){
                sbSQL.append(" ,isenable");
                sbValue.append(",?");
                strValue=strValue+roleInfo.getIsenable()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }

            if(!StringUtils.isEmpty(roleInfo.getCreatebyid())){
                sbSQL.append(" ,createbyid");
                sbValue.append(" ,?");
                strValue=strValue+roleInfo.getCreatebyid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(roleInfo.getLastmodifybyid())){
                sbSQL.append(" ,lastmodifybyid");
                sbValue.append(" ,?");
                strValue=strValue+roleInfo.getLastmodifybyid()+",";
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

            logger.debug("本次执行新增角色的sql为:" + sbSQL.toString() );

            // 执行SQL
            jdbcTemplate.update(sbSQL.toString() ,objParams ,intTypes);
            return ArkCommonResult.ok();

        }catch (Exception e){
            logger.error("新增角色发生异常：" ,e);
            return ArkCommonResult.fail("新增角色发生异常:" + e.getMessage());
        }finally {
            sbSQL = null;
            sbValue = null;
            strValue = null;
            strType = null;
        }
    }

    /**
     * 更新角色
     *
     * @param roleInfo
     * @return
     */
    @Override
    public ArkCommonResult update(RoleInfo roleInfo) {
        logger.info("更新角色参数为：" + roleInfo );
        int intLength=0;
        String strValue="";
        String strType="";
        StringBuffer sbSQL = new StringBuffer();

        try{

            sbSQL.append(" UPDATE role_info SET ");


            if(!StringUtils.isEmpty(roleInfo.getRolename())){
                sbSQL.append(" rolename = ? ,");
                strValue=strValue+roleInfo.getRolename()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(roleInfo.getIsenable())){
                sbSQL.append(" isenable = ? ,");
                strValue=strValue+roleInfo.getIsenable()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(roleInfo.getLastmodifybyid())){
                sbSQL.append(" lastmodifybyid = ? ,");
                strValue=strValue+roleInfo.getLastmodifybyid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            sbSQL.deleteCharAt(sbSQL.length()-1);
            sbSQL.append(" WHERE roleid = ?");
            strValue=strValue+roleInfo.getRoleid()+",";
            strType=strType+java.sql.Types.VARCHAR+",";
            intLength++;

            Object[] objParams= strValue.split(",");
            int[] intTypes = new int[intLength];

            String[] strTypes = strType.split(",");
            for (int i = 0 ; i < strTypes.length ;i++  ){
                intTypes[i] = Integer.parseInt(strTypes[i]);
            }

            logger.info("本次执行更新角色的sql为:" + sbSQL.toString() );

            // 执行SQL
            jdbcTemplate.update(sbSQL.toString() ,objParams ,intTypes);
            return ArkCommonResult.ok();
        }catch (Exception e){
            logger.error("更新角色发生异常：" ,e);
            return ArkCommonResult.fail("更新角色发生异常:" + e.getMessage());
        }finally {
            sbSQL = null;
            strValue = null;
            strType = null;
        }
    }


    /**
     * 检查角色名称是否存在
     * @param roleInfo
     * @return
     */
    public ArkCommonResult checkRolename(RoleInfo roleInfo){
        logger.info("查询用户列表参数为：" + roleInfo );
        //查询语句
        StringBuffer sbSql = new StringBuffer();
        try{
            sbSql.append(" select roleid"  );
            sbSql.append(" from role_info"  );
            sbSql.append(" where rolename = ? "  );

            logger.info("检查角色名称是否存在sql为" + sbSql.toString());
            Object[] params = new Object[] {roleInfo.getRolename().trim() };
            List<String> strRoleids =  jdbcTemplate.queryForList(sbSql.toString(), params, String.class);
            if(strRoleids!=null && strRoleids.size() > 0 )
                return ArkCommonResult.fail("角色已经存在");
        }catch (Exception e){
            logger.error("检查角色名称是否存在发生异常" ,e);
            return ArkCommonResult.fail("检查角色名称是否存在发生异常：" + e.getMessage());
        }finally {
            sbSql = null;
        }
        return ArkCommonResult.ok();
    }
}
