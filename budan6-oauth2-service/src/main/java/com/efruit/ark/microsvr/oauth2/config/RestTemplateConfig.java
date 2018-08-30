package com.efruit.ark.microsvr.oauth2.config;


import org.springframework.beans.factory.annotation.Value;
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
		return headers;
	}

}
