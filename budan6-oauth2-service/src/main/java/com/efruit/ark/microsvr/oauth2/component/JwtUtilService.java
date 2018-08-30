package com.efruit.ark.microsvr.oauth2.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JWT工具类
 * Created by yangyang on 2018/8/21.
 */
@Component
public class JwtUtilService {
    private static final String CLAIM_KEY_ISS = "created";
    private static final String CLAIM_KEY_AUD = "ark";

    @Value("${jwt.secret}")
    private String secret; //秘钥

    @Value("${jwt.expiration}")
    private Long expiration; //过期时间


    /**
     * 生成token
     * @param loginAuthUser
     * @return
     */
    public String genToken(LoginAuthUser loginAuthUser){
        Map mapClaims = new HashMap();
        long longNowMillis = System.currentTimeMillis();
        mapClaims.put("iss", loginAuthUser.getUserid());//存放用户的id
        mapClaims.put("created", new Date());
        mapClaims.put("sub", loginAuthUser.getUsername());//jwt所面向的用户
        mapClaims.put("aud", CLAIM_KEY_AUD);//接收jwt的一方
        mapClaims.put("exp", new Date(longNowMillis + expiration));// jwt的过期时间，这个过期时间必须要大于签发时间
        mapClaims.put("iat", new Date(longNowMillis));//jwt的签发时间
        //设置可访问的路径
        mapClaims.put("authUrl", loginAuthUser.getAuthorities());

        JwtBuilder jwtBuilder = Jwts.builder().setClaims(mapClaims);
        Key SIGNIG_KEY = new SecretKeySpec(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(secret),
                SignatureAlgorithm.HS256.getJcaName());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, SIGNIG_KEY);
        return jwtBuilder.compact();
    }


    /**
     * 验证token
     * @param token
     * @param requestUrl
     * @return
     */
    public boolean verifyRegisterLink(String token,String requestUrl){
        boolean blnContinueRequst = false;
        try{
            //1 根据ticket反解析出相应的jwt
            Map<String, Object> jwtMap = null ;
            try{
                // 使用私钥验证TOKEN是否合法，合法则还原为Claims对象
                Key SIGNIG_KEY = new SecretKeySpec(Base64.decodeBase64(secret),
                        SignatureAlgorithm.HS256.getJcaName());
                Claims claims = Jwts.parser().setSigningKey(SIGNIG_KEY)
                        .parseClaimsJws(token.trim()).getBody();
                jwtMap = (Map<String, Object>)claims;
            }catch(Exception e){
                e.printStackTrace();
            }

            //System.out.println("本次解析出的Json为:" + jwtMap.toString());
            //判断是否过期
            if((new Date((Long)jwtMap.get("exp")).before(new Date()))){
                //判断访问的url是否在允许的范围内
                List<Map> listAuthUrl = (List) jwtMap.get("authUrl");

                Pattern pattern = null;
                Matcher matcher = null;
                for (Map map:listAuthUrl) {
                    String authority = map.get("authority").toString();
                    pattern = Pattern.compile(authority);
                    matcher = pattern.matcher(requestUrl);
                    if(matcher.find()){
                        return true;
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return blnContinueRequst;
    }


    public static void main(String[] args) {
        JwtUtilService jwtUtilService = new JwtUtilService();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YW5neWFuZ0BlZnJ1aXRwcm8uY29tIiwiYXVkIjoiYXJrIiwiYXV0aFVybCI6W3siYXV0aG9yaXR5IjoiL21hbmFnZS8qLyoifSx7ImF1dGhvcml0eSI6Ii90ZXN0L3VzZXIvKiJ9XSwiY3JlYXRlZCI6MTUzNTAyODA5MDY5NiwiaXNzIjoiY3JlYXRlZCIsImV4cCI6MTUzNTAyODY5MDY5NiwiaWF0IjoxNTM1MDI4MDkwNjk2fQ.cxZq6lB1d77g3rTu8wuSqiDlYgFu8mwL2OPB7LQcH5U";
        String url = "/manage/1/3";
        System.out.println(jwtUtilService.verifyRegisterLink(token,url));
//        System.out.println(jwtUtilService.vailUrl("/manage/1/2","/manage/*/*"));
    }
}
