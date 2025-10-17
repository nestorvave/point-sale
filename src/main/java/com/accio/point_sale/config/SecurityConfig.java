package com.accio.point_sale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable()) // Desactiva protección CSRF para pruebas
				.authorizeHttpRequests(auth -> auth
						.anyRequest().permitAll() // Permite todas las peticiones
				)
				.formLogin(login -> login.disable()); // Desactiva el login automático

		return http.build();
	}
}