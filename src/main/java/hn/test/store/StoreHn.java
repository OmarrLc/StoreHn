package hn.test.store;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import hn.test.store.configuration.MyPropertiesConfig;
import hn.test.store.entity.CustomerEntity;
import hn.test.store.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class StoreHn  implements CommandLineRunner {
	
	
	@Autowired
	private MyPropertiesConfig myProperties;
	
	@Autowired
	private CustomerRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(StoreHn.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		log.info("Backend microservices of project " + myProperties.getApplicatioName() + " Started in Environment " + myProperties.getProfileActive() + " Accros the port: " + myProperties.getPort());
		
		repo.save(CustomerEntity.builder()
			.customerId(1L)
			.customerName("Default")
			.registerDate(LocalDate.now().toString())
			.registerTime(LocalTime.now().toString())
			.statusRegister("ACTIVE").build());
	}

}
