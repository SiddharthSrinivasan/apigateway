package com.project.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.path("/hello-service/**")
						.filters(f -> f.rewritePath("/hello-service/(?<RID>.*)", "/${RID}"))
						.uri("http://localhost:8080/"))
				.route(p -> p
						.path("/python-server/**")
						.filters(f -> f.rewritePath("/python-server/(?<RID>.*)", "/${RID}"))
						.uri("http://localhost:8000/"))
				.build();
	}

}
