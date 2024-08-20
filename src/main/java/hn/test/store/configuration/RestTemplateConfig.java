package hn.test.store.configuration;

import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import hn.test.store.exception.CustomResponseErrorHandler;
import hn.test.store.util.Utility;

@Configuration
public class RestTemplateConfig {
	
	@Autowired
	Utility util;
	
	@Autowired
	MyPropertiesConfig myProperties;
	
	@Bean
	RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(30000);
		factory.setReadTimeout(50000);

		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setErrorHandler(new CustomResponseErrorHandler());

		return restTemplate;
	}
}
