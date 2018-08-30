package com.efruit.ark.microsvr.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by yangyang on 2017/6/8
 */
@Configuration
public class RestTemplateConfig {

//	@Value("${spring.security.user.name}")
//	private String securityName ;
//
//	@Value("${spring.security.user.password}")
//	private String securityPwd;

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(15000);// ms
		factory.setConnectTimeout(15000);// ms
		return factory;
	}


	//定义一个Bean修改头信息进行客户端认证
	@Bean
	public HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		//认证的原始信息
		StringBuffer sbAuth = new StringBuffer();
//		sbAuth.append(securityName).append(":").append(securityPwd);
		//将原始认证信息进行Base64加密
		byte[] encodeAuth = Base64.getEncoder().encode(sbAuth.toString().getBytes(Charset.forName("US-ASCII")));
		sbAuth = null;
		//加密后的认证信息要与Basic有个空格
		String authHeader = "Basic "+new String(encodeAuth);
		headers.set("Authorization", authHeader);
		return headers;
	}


	public static void main(String[] args) {
		//认证的原始信息
		StringBuffer sbAuth = new StringBuffer();
		sbAuth.append("client").append(":").append("secret");
		//将原始认证信息进行Base64加密
		byte[] encodeAuth = Base64.getEncoder().encode(sbAuth.toString().getBytes(Charset.forName("US-ASCII")));
		sbAuth = null;
		//加密后的认证信息要与Basic有个空格
		String authHeader = "Basic "+new String(encodeAuth);
		System.out.println(authHeader);
	}
}
