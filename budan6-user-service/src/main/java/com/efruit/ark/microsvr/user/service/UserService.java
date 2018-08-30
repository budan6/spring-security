package com.efruit.ark.microsvr.user.service;


import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.UserInfo;

import java.util.Map;

/**
 * 用户服务接口
 * Created by yangyang on 2018/8/16.
 */
public interface UserService {

    /**
     * 查询用户列表
     * @param userInfo
     * @return
     */
    ArkCommonResult list(UserInfo userInfo);

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    ArkCommonResult insert(UserInfo userInfo);


    /**
     * 更新用户
     * @param userInfo
     * @return
     */
    ArkCommonResult update(UserInfo userInfo);

    /**
     * 检查用户是否存在
     * @param userInfo
     * @return
     */
    ArkCommonResult check(UserInfo userInfo);

    /**
     * 根据用户id获取用户详细
     * @param userid
     * @return
     */
    ArkCommonResult getDetail(String userid);
}

