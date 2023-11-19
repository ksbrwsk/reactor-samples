package de.ksbrwsk.reactor.simple;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.math.MathFlux;

public class MathFluxExample {
    // create example for MathFlux min()
    // create example for MathFlux max()
    // create example for MathFlux sum()
    // create example for MathFlux average()
    // create example for MathFlux count()


    @Test
    void min() {
        Flux<Integer> range = Flux.range(1, 10);
        MathFlux.min(range).subscribe(System.out::println);
    }

    @Test
    void max() {
        Flux<Integer> range = Flux.range(1, 10);
        MathFlux.max(range).subscribe(System.out::println);
    }

    @Test
    void sum() {
        Flux<Integer> range = Flux.range(1, 10);
        MathFlux.sumInt(range).subscribe(System.out::println);
    }

    @Test
    void average() {
        Flux<Integer> range = Flux.range(1, 10);
        MathFlux.averageDouble(range).subscribe(System.out::println);
    }
}
