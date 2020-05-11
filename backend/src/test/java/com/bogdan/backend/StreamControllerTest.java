package com.bogdan.backend;

import com.bogdan.backend.dto.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest
class StreamControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void streamData() {
        StepVerifier.create(
                webTestClient.get().uri("/stream")
                        .accept(MediaType.APPLICATION_STREAM_JSON)
                        .exchange()
                        .expectStatus().isOk()
                        .returnResult(Item.class).getResponseBody()
        )
                .expectSubscription()
                .thenAwait()
                .expectNextCount(5)
                .thenCancel()
                .verify();


    }
}