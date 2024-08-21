package hn.test.store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).headers(headers -> headers.frameOptions().disable())
				.authorizeHttpRequests(requests -> requests.requestMatchers(new AntPathRequestMatcher("/api/v1/**"),
						new AntPathRequestMatcher("/swagger-ui/**"), new AntPathRequestMatcher("/swagger-ui.html"),
						new AntPathRequestMatcher("/v3/api-docs/**"), new AntPathRequestMatcher("/v3/api-docs.yaml"),
						new AntPathRequestMatcher("/api-docs-TEST STORE/**"), new AntPathRequestMatcher("/h2-console/**"))
						.permitAll().anyRequest().authenticated());
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("pass").roles("rol").build());
		return manager;
	}
}
