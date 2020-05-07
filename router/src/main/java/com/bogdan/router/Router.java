package com.bogdan.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> routeProxy(ProxyHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.path("/proxy"), handler::handleRoute);
    }

    @Bean
    public RouterFunction<ServerResponse> routeRedirect(RedirectHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.path("/redirect"), handler::handleRoute);
    }
}
