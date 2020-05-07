package com.bogdan.backend;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@RestController
public class StreamController {

    @RequestMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamData() {
        return Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).map(
                i -> LocalTime.now().toString()
        );
    }
}
