package com.gateway.demoapigateway;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@EnableEurekaClient
public class DemoApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiGatewayApplication.class, args);
		
		
		
	}
	@Bean
	public CorsWebFilter corsWebFilter() {



	CorsConfiguration corsConfig = new CorsConfiguration();
	corsConfig.addAllowedOrigin("*");
	//corsConfig.setAllowedOrigins(Collections.singletonList("*"));
	corsConfig.setMaxAge(3600L);
	corsConfig.setAllowedMethods(Arrays.asList("GET", "POST"));
	corsConfig.addAllowedHeader("*");
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", corsConfig);



	return new CorsWebFilter(source);
	}
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
	return builder.routes().route("JWT", r -> r.path("/authenticate/**").uri("lb://JWT"))
	.route("PENSION", r -> r.path("/ProcessPension/**","/thepension/**").uri("lb://PENSION")).build();
	}
	
}
