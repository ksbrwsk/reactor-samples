package de.ksbrwsk.reactor.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import javax.management.ValueExp;
import java.net.Inet4Address;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThenManyTest {

    @Test
    void thenMany() {
        var letters = new AtomicInteger();
        var numbers = new AtomicInteger();
        Flux<String> lettersPublisher = Flux.just("a", "b", "c")
                .doOnNext(value -> letters.incrementAndGet());
        Flux<Integer> numbersPublisher = Flux.just(1, 2, 3)
                .doOnNext(value -> numbers.incrementAndGet());

        Flux<Integer> lettersBeforeNumbers = lettersPublisher.thenMany(numbersPublisher);
        StepVerifier
                .create(lettersBeforeNumbers)
                .expectNext(1, 2, 3)
                .verifyComplete();
        assertEquals(letters.get(), 3);
        assertEquals(numbers.get(), 3);

    }
}
