package com.bogdan.backend;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RouterTest {

    Router router = new Router();
    BackendHandler backendHandler = mock(BackendHandler.class);

    @Test
    public void expectOkResponse_WhenHandlerRespondsWithOk() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(router.routeBackend(backendHandler))
                .build();
        given(backendHandler.handleRoute(any())).willReturn(ServerResponse.ok().build());

        client.get()
                .uri("/backend")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void expected500Error_WhenHandlerThrowsException() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(router.routeBackend(backendHandler))
                .build();

        given(backendHandler.handleRoute(any())).willReturn(Mono.error(new RuntimeException("oops")));

        client.get()
                .uri("/backend")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
