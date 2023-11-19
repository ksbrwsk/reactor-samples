package de.ksbrwsk.reactor.simple;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SimpleFluxFactoriesTest {

    @Test
    void simple() {
        Publisher<Integer> rangeOfIntegers =
                Flux.range(0, 10);
        StepVerifier
                .create(rangeOfIntegers)
                .expectNextCount(10)
                .verifyComplete();

        Flux<String> letters = Flux.just("A", "B", "C");
        StepVerifier
                .create(letters)
                .expectNext("A", "B", "C")
                .verifyComplete();

        long now = System.currentTimeMillis();
        Mono<Date> dateMono = Mono.just(new Date(now));
        StepVerifier
                .create(dateMono)
                .expectNext(new Date(now))
                .verifyComplete();

        Mono<Object> empty = Mono.empty();
        StepVerifier
                .create(empty)
                .verifyComplete();

        Flux<Integer> fromArray = Flux.fromArray(new Integer[]{1, 2, 3});
        StepVerifier
                .create(fromArray)
                .expectNext(1, 2, 3)
                .verifyComplete();

        Flux<Integer> fromIterable = Flux.fromIterable(Arrays.asList(1, 2, 3));
        StepVerifier
                .create(fromIterable)
                .expectNext(1, 2, 3)
                .verifyComplete();

        AtomicInteger atomicInteger = new AtomicInteger();
        Supplier<Integer> supplier = atomicInteger::incrementAndGet;
        Flux<Integer> integerFlux = Flux.fromStream(Stream.generate(supplier));
        StepVerifier
                .create(integerFlux.take(3))
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }
}
