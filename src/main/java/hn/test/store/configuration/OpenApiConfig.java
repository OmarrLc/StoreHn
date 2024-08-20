package hn.test.store.configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()).info(new Info()
				.title("API´s Store Hn")
				.description(
						"")
				.contact(new Contact().name(
						"Omar Lopez")
						.url("").email("omar.colg97@gmail.com"))
				.version("v1.0.0"));
	}
}
