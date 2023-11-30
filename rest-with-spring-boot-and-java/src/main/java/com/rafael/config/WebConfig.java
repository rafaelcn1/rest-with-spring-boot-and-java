package com.rafael.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	/*
	 * Configuração cors de forma global
	 * */
	@Value("${cors:originPatterns:default}")
	private String corsOriginPatters = "";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] allowedOrigins = corsOriginPatters.split(",");
		registry.addMapping("/**")
			//.allowedMethods("GET", "POST", "DELETE"); // Escolher metodos especificos
			.allowedMethods("*") // Escolher todos os metodos
			.allowedOrigins(allowedOrigins)
			.allowCredentials(true);
	}
	
	
	

}
