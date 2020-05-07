package com.bogdan.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProxyHandler {

    private WebClient webClient = WebClient.create();

    private UrlResolver urlResolver;

    public ProxyHandler(@Autowired UrlResolver urlResolver) {
        this.urlResolver = urlResolver;
    }

    public Mono<ServerResponse> handleRoute(ServerRequest request) {

        return webClient
                .method(request.method())
                .uri(urlResolver.resolveUrl())
                .headers(httpHeaders -> httpHeaders.setAll(request.headers().asHttpHeaders().toSingleValueMap()))
                .exchange()
                .flatMap(clientResponse -> ServerResponse
                        .status(clientResponse.statusCode())
                        .headers(httpHeaders -> clientResponse.headers().asHttpHeaders().forEach((n, v) -> httpHeaders.put(n, v)))
                        .body(BodyInserters.fromDataBuffers(clientResponse.bodyToFlux(DataBuffer.class)))
                );
    }

}