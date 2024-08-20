package hn.test.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import hn.test.store.configuration.MyPropertiesConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class StoreHn  implements CommandLineRunner {
	
	
	@Autowired
	private MyPropertiesConfig myProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(StoreHn.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		log.info("Backend microservices of project " + myProperties.getApplicatioName() + " Started in Environment " + myProperties.getProfileActive() + " Accros the port: " + myProperties.getPort());
	}

}
