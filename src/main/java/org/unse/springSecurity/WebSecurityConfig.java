package org.unse.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.unse.springSecurity.filtro.SecurityFilter;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
		return http.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/usuarios/login/")
				.permitAll()
				.requestMatchers(HttpMethod.POST, "/usuarios/alta/")
				.permitAll()
				.requestMatchers(HttpMethod.PUT, "/usuarios/actualizar/contrasenia/")
				.permitAll()
				.requestMatchers(HttpMethod.GET, "/eventos/listado/general")
				.permitAll()
				.requestMatchers(HttpMethod.GET, "/eventos/listado/filtrado")
				.permitAll()
				.requestMatchers("/documentacion/**", "/v3/api-docs/**","/v3/api-docs.yaml")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	

	
}
