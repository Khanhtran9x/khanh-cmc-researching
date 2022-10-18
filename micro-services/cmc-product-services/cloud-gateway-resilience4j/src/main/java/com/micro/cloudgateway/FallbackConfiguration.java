package com.micro.cloudgateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class FallbackConfiguration {

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions
				.route(RequestPredicates.GET("/serviceFallBack"), this::handleFallBack)
				.andRoute(RequestPredicates.POST("/serviceFallBack"), this::handleFallBack);
	}

	public Mono<ServerResponse> handleFallBack(ServerRequest request) {
		return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build();
	}
}
