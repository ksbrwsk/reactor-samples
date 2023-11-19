package de.ksbrwsk.reactor.simple;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class SwitchMapTest {

    @Test
    void switchMapWithLookaheads() {
        Flux<String> source =
                Flux.just("re", "rea", "reac", "react", "reacti", "reactiv", "reactive")
                        .delayElements(Duration.ofMillis(100))
                        .switchMap(this::lookup);
        StepVerifier
                .create(source)
                .expectNext("reactive -> reactive")
                .verifyComplete();
    }

    private Flux<String> lookup(String word) {
        return Flux.just(word + " -> reactive")
                .delayElements(Duration.ofMillis(500));
    }
}
