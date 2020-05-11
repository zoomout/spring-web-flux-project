package com.bogdan.backend;

import com.bogdan.backend.dto.Item;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalTime;

@RestController
public class StreamController {

    @RequestMapping(value = "/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Item> streamData() {
        return Flux.just(1, 2, 3, 4, 5).map(
                i -> new Item(i, LocalTime.now().toString())
        );
    }
}
