package de.ksbrwsk.reactor.simple;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

public class EmitterProcessorTest {

    @Test
    void emitterProcessor() {
        EmitterProcessor<String> processor = EmitterProcessor.create();
        produce(processor.sink());
        consume(processor);
    }

    private void consume(EmitterProcessor<String> processor) {
        StepVerifier
                .create(processor)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .expectNext("4")
                .expectNext("5")
                .verifyComplete();
    }

    private void produce(FluxSink<String> sink) {
        sink.next("1");
        sink.next("2");
        sink.next("3");
        sink.next("4");
        sink.next("5");
        sink.complete();
    }

}
