package com.bogdan.router;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class RedirectHandler {

    public Mono<ServerResponse> handleRoute(ServerRequest request) {
        return ServerResponse.status(HttpStatus.FOUND).header("Location", "/backend").build();
    }

}