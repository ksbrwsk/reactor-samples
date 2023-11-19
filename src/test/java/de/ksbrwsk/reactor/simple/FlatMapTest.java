package de.ksbrwsk.reactor.simple;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FlatMapTest {

    @Test
    void flatMap() {
        Flux<Integer> data = Flux
                .just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(id -> this.delayReplyFor(id.id, id.delay));
        StepVerifier
                .create(data)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }

    private Flux<Integer> delayReplyFor(int id, long delay) {
        return Flux.just(id)
                .delayElements(Duration.ofMillis(delay));
    }

    @AllArgsConstructor
    static class Pair {
        int id;
        long delay;
    }
}
