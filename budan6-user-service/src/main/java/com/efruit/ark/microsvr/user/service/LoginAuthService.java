package com.efruit.ark.microsvr.user.service;


import com.efruit.ark.common.ArkCommonResult;
import com.efruit.ark.microsvr.user.dao.domain.UserInfo;

import java.util.Map;

/**
 * 用户服务接口
 * Created by yangyang on 2018/8/16.
 */
public interface LoginAuthService {

    /**
     * 登录认证
     * @param loginName：登录账号
     * @return
     */
    Map<String,Object> auth(String loginName);

}

