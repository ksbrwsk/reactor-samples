package de.ksbrwsk.reactor.simple;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ConcatMapTest {

    @Test
    void concatMap() {
        Flux<Integer> data = Flux
                .just(new FlatMapTest.Pair(1, 300), new FlatMapTest.Pair(2, 200), new FlatMapTest.Pair(3, 100))
                .concatMap(id -> this.delayReplyFor(id.id, id.delay));
        StepVerifier
                .create(data)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    private Flux<Integer> delayReplyFor(int id, long delay) {
        return Flux.just(id)
                .delayElements(Duration.ofMillis(delay));
    }

    @AllArgsConstructor
    static class Pair {
        private int id;
        private long delay;
    }
}
