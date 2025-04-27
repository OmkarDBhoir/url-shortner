package com.shorturl.shorturl.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsFilter {
	
	@Bean
	public CorsConfigurationSource configurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("POST", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-type"));
		configuration.setAllowCredentials(true);
		
		
		CorsConfiguration getConfig = new CorsConfiguration();
		getConfig.setAllowedOrigins(Arrays.asList("*"));
		getConfig.setAllowedMethods(Arrays.asList("GET", "OPTIONS"));
		getConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-type"));
		getConfig.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/url/shorten", configuration);
		source.registerCorsConfiguration("/**", getConfig);
		
		return source;
	}
}
