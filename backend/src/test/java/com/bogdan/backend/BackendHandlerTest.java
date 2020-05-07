package com.bogdan.backend;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

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
