package de.ksbrwsk.reactor.simple;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TakeTest {

    @Test
    void take() {
        var count = 10;
        Flux<Integer> integerFlux = range().take(count);
        StepVerifier
                .create(integerFlux)
                .expectNextCount(count)
                .verifyComplete();
    }

    private Flux<Integer> range() {
        return Flux.range(0, 1000);
    }
}
