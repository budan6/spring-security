package com.efruit.ark.microsvr.user.component;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JWT工具类
 * Created by yangyang on 2018/8/21.
 */
@Component
public class JwtUtilService {

    @Value("${jwt.secret}")
    private String secret; //秘钥

    /**
     * 验证token
     * @param strToken 请求头token
     * @param requestUrl 请求url
     * @return
     */
    public boolean verifyRegisterLink(String strToken,String requestUrl){
        boolean blnContinueRequst = false;
        try{
            //1 根据token反解析出相应的jwt
            Claims claims = getClaimsByToken(strToken);
            System.out.println("本次解析出的Json为:" + claims.toString());
            //判断是否过期
            if((new Date((Long)claims.get("exp")).after(new Date()))){
                //判断访问的url是否在允许的范围内
//                System.out.println("判断访问的url是否在允许的范围内");
                List<Map> listAuthUrl = (List) claims.get("authUrl");

                Pattern pattern = null;
                Matcher matcher = null;
                for (Map map:listAuthUrl) {
                    String authority = map.get("authority").toString();
                    pattern = Pattern.compile(authority);
                    matcher = pattern.matcher(requestUrl);
                    System.out.println("pattern==" + pattern);
                    System.out.println("matcher==" + matcher);
                    if(matcher.find()){
                        return true;
                    }
                }
            }else{
                System.out.println("token时间已经过期");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return blnContinueRequst;
    }


    private Claims getClaimsByToken(String strToken){
        // 使用私钥验证TOKEN是否合法，合法则还原为Claims对象
        Key SIGNIG_KEY = new SecretKeySpec(Base64.decodeBase64(secret),
                SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parser().setSigningKey(SIGNIG_KEY)
                .parseClaimsJws(strToken.trim()).getBody();
    }


    public String genUsername(String strToken){
        return getClaimsByToken(strToken).get("sub").toString();
    }

    public String genUserid(String strToken){
        return getClaimsByToken(strToken).get("iss").toString();
    }


    public static void main(String[] args) {
        JwtUtilService jwtUtilService = new JwtUtilService();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YW5neWFuZ0BlZnJ1aXRwcm8uY29tIiwiYXVkIjoiYXJrIiwiYXV0aFVybCI6W3siYXV0aG9yaXR5IjoiL21hbmFnZS8qLyoifV0sImNyZWF0ZWQiOjE1MzUxMDM1NTA2NzUsImlzcyI6IjAyMjk1NDg2YmIyZTQzM2JiNmY3N2IyN2FkM2U0MGY0IiwiZXhwIjoxNTQxMTAzNTUwNjc1LCJpYXQiOjE1MzUxMDM1NTA2NzV9.HthZt6UPEGofQA0cVcU44lnu_RMZ9kWsgmbZz2ghoHc";
        String url = "/manage/1/3";
//        System.out.println(jwtUtilService.verifyRegisterLink(token,url));
//        System.out.println(jwtUtilService.vailUrl("/manage/1/2","/manage/*/*"));
        System.out.println(new Date((Long.parseLong("1541103550675"))));
        System.out.println(new Date());
        System.out.println((new Date((Long.parseLong("1541103550675"))).after(new Date())));//1541103550675
    }
}
