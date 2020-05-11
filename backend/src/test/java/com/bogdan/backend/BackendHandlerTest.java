package com.bogdan.backend;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class BackendHandlerTest {
    private Router router = new Router();
    private BackendHandler backendHandler;

    @Before
    public void resetClassUnderTest() {
        backendHandler = new BackendHandler();
    }

    @Test
    public void expectOkResponseFromBackend() throws InterruptedException {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(router.routeBackend(backendHandler))
                .build();

        client.get()
                .uri("/backend")
                .exchange()
                .expectStatus()
                .isOk();
    }


}
