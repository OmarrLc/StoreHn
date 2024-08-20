package hn.test.store.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Getter
@Configuration
@PropertySource (value = { "classpath:application.properties" }, ignoreResourceNotFound = true)
public class MyPropertiesConfig {
	
	@Value("${spring.application.name}")
	private String applicatioName;
	
	@Value("${spring.profiles.active}")
	private String profileActive;
	
	@Value("${server.port}")
	private String port;
	
	@Value("${certificate}")
	private String certificate;



}
