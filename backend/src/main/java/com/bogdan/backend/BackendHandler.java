package com.bogdan.backend;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BackendHandler {

    public Mono<ServerResponse> handleRoute(ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromObject("Response from backend!"));
    }
}