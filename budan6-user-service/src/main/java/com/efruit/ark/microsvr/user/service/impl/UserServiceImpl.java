package com.efruit.ark.microsvr.user.service.impl;

import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.QueryResult;
import com.efruit.ark.microsvr.user.dao.domain.UserInfo;
import com.efruit.ark.microsvr.user.dao.domain.dto.UserInfoDto;
import com.efruit.ark.microsvr.user.dao.mapper.UserAuthMapper;
import com.efruit.ark.microsvr.user.dao.mapper.UserInfoDtoMapper;
import com.efruit.ark.microsvr.user.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 用户服务接口实现
 * Created by yangyang on 2018/8/16.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 查询用户列表
     * @param userInfo
     * @return
     */
    @Override
    public ArkCommonResult list(UserInfo userInfo) {
        logger.info("查询用户列表参数为：" + userInfo );
        //查询语句
        StringBuffer sbSql = new StringBuffer();
        try{
            sbSql.append(" select user.userid,user.loginname,user.nickname,user.password,user.wechatid,user.wechatheadimg," );
            sbSql.append(" user.openid,user.sex,user.isenable,user.region,user.roleid,user.createdate,user.createbyid,user.usertype,"  );
            sbSql.append(" user.lastmodifydate,user.lastmodifybyid,createuser.nickname as createbyname,user.phone,"  );
            sbSql.append(" lastmodifyuser.nickname as lastmodifybyname,role_info.rolename as rolename"  );
            sbSql.append(" from user_info user left join user_info createuser on user.createbyid = createuser.userid"  );
            sbSql.append(" left join user_info lastmodifyuser on user.lastmodifybyid = lastmodifyuser.userid, "  );
            sbSql.append(" role_info role_info "  );
            sbSql.append(" where user.roleid = role_info.roleid ");

            if(!StringUtils.isEmpty(userInfo.getWechatid()))
                sbSql.append(" and instr(user.wechatid ,'" + userInfo.getWechatid().trim() + "')>0");

            if(!StringUtils.isEmpty(userInfo.getLoginname()))
                sbSql.append(" and instr(user.loginname ,'" + userInfo.getLoginname().trim() + "')>0");

            if(!StringUtils.isEmpty(userInfo.getSex()))
                sbSql.append(" and user.sex = '" + userInfo.getSex() + "' ");

            if(!StringUtils.isEmpty(userInfo.getIsenable()))
                sbSql.append(" and user.isenable = '" + userInfo.getIsenable() + "' ");

            logger.info("查询用户列表的sql为" + sbSql.toString());
            List<UserInfoDto> listUserInfo = jdbcTemplate.query(sbSql.toString(), new UserInfoDtoMapper());
            QueryResult queryResult = new QueryResult();
            queryResult.setCount(listUserInfo != null && listUserInfo.size() > 0 ? listUserInfo.size() : 0 );
            queryResult.setList(listUserInfo);
            return ArkCommonResult.ok(queryResult);
        }catch (Exception e){
            logger.error("查询用户列表发生异常" ,e);
            return ArkCommonResult.build(819,"查询用户列表发生异常：" + e.getMessage());
        }finally {
            sbSql = null;
        }
    }

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    @Override
    public ArkCommonResult insert(UserInfo userInfo) {
        logger.info("新增用户参数为：" + userInfo);
        int intLength=0;
        String strValue="";
        String strType="";

        StringBuffer sbSQL = new StringBuffer();
        StringBuffer sbValue = new StringBuffer();
        try{

            sbSQL.append(" insert into user_info (");

            if(!StringUtils.isEmpty(userInfo.getUserid())){
                sbSQL.append(" userid");
                sbValue.append(" ?");
                strValue=strValue+userInfo.getUserid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getUsertype())){
                sbSQL.append(" ,usertype");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getUsertype()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getRoleid())){
                sbSQL.append(" ,roleid");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getRoleid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getNickname())){
                sbSQL.append(" ,nickname");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getNickname()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getLoginname())){
                sbSQL.append(" ,loginname");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getLoginname()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getPassword())){
                sbSQL.append(" ,password");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getPassword()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getWechatid())){
                sbSQL.append(" ,wechatid");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getWechatid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getWechatheadimg())){
                sbSQL.append(" ,wechatheadimg");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getWechatheadimg()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getOpenid())){
                sbSQL.append(" ,openid");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getOpenid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getSex())){
                sbSQL.append(" ,sex");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getSex()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getPhone())){
                sbSQL.append(" ,phone");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getPhone()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getRegion())){
                sbSQL.append(" ,region");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getRegion()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getIsenable())){
                sbSQL.append(" ,isenable");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getIsenable()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getCreatebyid())){
                sbSQL.append(" ,createbyid");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getCreatebyid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";

                intLength++;
            }
            if(!StringUtils.isEmpty(userInfo.getLastmodifybyid())){
                sbSQL.append(" ,lastmodifybyid");
                sbValue.append(" ,?");
                strValue=strValue+userInfo.getLastmodifybyid()+",";
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

            logger.debug("本次执行新增用户的sql为:" + sbSQL.toString() );

            // 执行SQL
            jdbcTemplate.update(sbSQL.toString() ,objParams ,intTypes);
            return ArkCommonResult.ok();

        }catch (Exception e){
            e.printStackTrace();
            logger.error("新增用户发生异常：",e);
            return ArkCommonResult.build(819,"新增用户发生异常:" + e.getMessage());
        }finally {
            sbSQL = null;
            strValue = null;
            strType = null;
            sbValue = null;
        }
    }

    /**
     * 更新用户
     *
     * @param userInfo
     * @return
     */
    @Override
    public ArkCommonResult update(UserInfo userInfo) {
        logger.info("更新用户参数为：" + userInfo);

        StringBuffer sbSQL = new StringBuffer();
        int intLength=0;
        String strValue="";
        String strType="";
        try{

            sbSQL.append(" UPDATE user_info SET ");


            if(!StringUtils.isEmpty(userInfo.getLoginname())){
                sbSQL.append(" loginname = ? ,");
                strValue=strValue+userInfo.getLoginname()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getNickname())){
                sbSQL.append(" nickname = ? ,");
                strValue=strValue+userInfo.getNickname()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getPassword())){
                sbSQL.append(" password = ? ,");
                strValue=strValue+userInfo.getPassword()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getWechatid())){
                sbSQL.append(" wechatid = ? ,");
                strValue=strValue+userInfo.getWechatid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getWechatheadimg())){
                sbSQL.append(" wechatheadimg = ? ,");
                strValue=strValue+userInfo.getWechatheadimg()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getOpenid())){
                sbSQL.append(" openid = ? ,");
                strValue=strValue+userInfo.getOpenid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getSex())){
                sbSQL.append(" sex = ? ,");
                strValue=strValue+userInfo.getSex()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getPhone())){
                sbSQL.append(" phone = ? ,");
                strValue=strValue+userInfo.getPhone()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getIsenable())){
                sbSQL.append(" isenable = ? ,");
                strValue=strValue+userInfo.getIsenable()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            if(!StringUtils.isEmpty(userInfo.getRegion())){
                sbSQL.append(" region = ? ,");
                strValue=strValue+userInfo.getRegion()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(userInfo.getRoleid())){
                sbSQL.append(" roleid = ? ,");
                strValue=strValue+userInfo.getRoleid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(userInfo.getUsertype())){
                sbSQL.append(" usertype = ? ,");
                strValue=strValue+userInfo.getUsertype()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }


            if(!StringUtils.isEmpty(userInfo.getLastmodifybyid())){
                sbSQL.append(" lastmodifybyid = ? ,");
                strValue=strValue+userInfo.getLastmodifybyid()+",";
                strType=strType+java.sql.Types.VARCHAR+",";
                intLength++;
            }

            sbSQL.deleteCharAt(sbSQL.length()-1);
            sbSQL.append(" WHERE userid = ?");
            strValue=strValue+userInfo.getUserid()+",";
            strType=strType+java.sql.Types.VARCHAR+",";
            intLength++;

            Object[] objParams= strValue.split(",");
            int[] intTypes = new int[intLength];

            String[] strTypes = strType.split(",");
            for (int i = 0 ; i < strTypes.length ;i++  ){
                intTypes[i] = Integer.parseInt(strTypes[i]);
            }

            logger.info("本次执行更新用户的sql为:" + sbSQL.toString() );

            // 执行SQL
            jdbcTemplate.update(sbSQL.toString() ,objParams ,intTypes);
            return ArkCommonResult.ok();

        }catch (Exception e){
            logger.error("更新用户发生异常：",e);
            return ArkCommonResult.build(819,"更新用户发生异常：" + e.getMessage());
        }finally {
            sbSQL = null;
            strValue = null;
            strType = null;
        }
    }


    /**
     * 检查用户是否存在
     * @param userInfo
     * @return
     */
    public ArkCommonResult check(UserInfo userInfo){
        logger.info("查询用户列表参数为：" + userInfo );
        //查询语句
        StringBuffer sbSql = new StringBuffer();
        try{
            sbSql.append(" select user.userid"  );
            sbSql.append(" from user_info user"  );
            sbSql.append(" where usertype = ? "  );

            if("manage".equals(userInfo.getUsertype()))
                sbSql.append(" and user.loginname = ?  ");
            else
                sbSql.append(" and user.openid = ? ");

            logger.info("检查用户是否存在sql为" + sbSql.toString());
            Object[] params = new Object[] {userInfo.getUsertype(), userInfo.getLoginname() };
            List<String> strUserids =  jdbcTemplate.queryForList(sbSql.toString(), params, String.class);
            if(strUserids!=null && strUserids.size() > 0 )
                return ArkCommonResult.fail("用户已经存在");
        }catch (Exception e){
            logger.error("检查用户是否存在发生异常" ,e);
            return ArkCommonResult.build(819,"检查用户是否存在发生异常：" + e.getMessage());
        }finally {
            sbSql = null;
        }
        return ArkCommonResult.ok();
    }

    /**
     * 根据用户id获取用户详细
     *
     * @param userid
     * @return
     */
    @Override
    public ArkCommonResult getDetail(String userid) {
        logger.info("根据用户id获取用户详细参数为：" + userid );
        //查询语句
        StringBuffer sbSql = new StringBuffer();
        try{
            sbSql.append(" select user.userid,user.loginname,user.nickname,user.password,user.wechatid,user.wechatheadimg," );
            sbSql.append(" user.openid,user.sex,user.isenable,user.region,user.roleid,user.createdate,user.createbyid,user.usertype,"  );
            sbSql.append(" user.lastmodifydate,user.lastmodifybyid,createuser.nickname as createbyname,user.phone,"  );
            sbSql.append(" lastmodifyuser.nickname as lastmodifybyname,role_info.rolename as rolename"  );
            sbSql.append(" from user_info user left join user_info createuser on user.createbyid = createuser.userid"  );
            sbSql.append(" left join user_info lastmodifyuser on user.lastmodifybyid = lastmodifyuser.userid, "  );
            sbSql.append(" role_info role_info "  );
            sbSql.append(" where user.roleid = role_info.roleid ");
            sbSql.append(" and user.userid = '" + userid + "' ");


            logger.info("根据用户id获取用户详细的sql为" + sbSql.toString());
            List<UserInfoDto> listUserInfo = jdbcTemplate.query(sbSql.toString(), new UserInfoDtoMapper());
            if(listUserInfo != null && listUserInfo.size()>0)
                return ArkCommonResult.ok(listUserInfo.get(0));
            else
                return ArkCommonResult.build(801,"用户不存在");
        }catch (Exception e){
            logger.error("根据用户id获取用户详细发生异常" ,e);
            return ArkCommonResult.build(819,"根据用户id获取用户详细发生异常：" + e.getMessage());
        }finally {
            sbSql = null;
        }
    }

}

