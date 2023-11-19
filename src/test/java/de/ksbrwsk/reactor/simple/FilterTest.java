package de.ksbrwsk.reactor.simple;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FilterTest {

    @Test
    void filter() {
        Flux<Integer> range = Flux.range(0, 1000).take(10);
        Flux<Integer> filter = range.filter(integer -> integer % 2 == 0);
        StepVerifier
                .create(filter)
                .expectNext(0, 2, 4, 6, 8)
                .verifyComplete();
    }
}
