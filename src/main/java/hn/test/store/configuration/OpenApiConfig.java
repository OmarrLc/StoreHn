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
				.title("Servicios de Liquidacion a Comercios Por Compras POS con QR - Modulo Cierres BASA")
				.description(
						"Documentacion Servicio BA-cierre-compras-qr-pos Service Documentation")
				.contact(new Contact().name(
						"Infatlan Equipo Soluciones| Developer Edison Ordoñez | Carlos Lopez")
						.url("").email("@"))
				.version("v1.0.0"));
	}
}
