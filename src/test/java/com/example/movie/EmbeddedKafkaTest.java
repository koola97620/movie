package com.example.movie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@EmbeddedKafka(
        topics = {"movie-test-topic"},
        partitions = 1,
        bootstrapServersProperty = "kafka.bootstrapAddress"
)
public class EmbeddedKafkaTest {
    @Test
    void context() {

    }
}
