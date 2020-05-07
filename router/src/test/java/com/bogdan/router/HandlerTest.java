package com.bogdan.router;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

public class HandlerTest {

    private static Router router;
    private static ProxyHandler proxyHandler;
    public static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        String url = "http://localhost:" + mockBackEnd.getPort() + "/backend";
        proxyHandler = new ProxyHandler(new TestUrlResolver(url));
        router = new Router();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void expectOkResponse_WhenBackendRespondsWithOk() throws InterruptedException {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(router.routeProxy(proxyHandler))
                .build();

        final var responseBody = "responseBody";
        mockBackEnd.enqueue(new MockResponse().setBody(responseBody).setResponseCode(200));
        int port = mockBackEnd.getPort();
        client.get()
                .uri("/proxy")
                .exchange()
                .expectStatus()
                .isOk();

        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        Assert.assertEquals("/backend", recordedRequest.getPath());
    }

}

class TestUrlResolver extends UrlResolver {

    private String url;

    TestUrlResolver(String url) {
        this.url = url;
    }

    @Override
    public String resolveUrl() {
        return url;
    }
}
