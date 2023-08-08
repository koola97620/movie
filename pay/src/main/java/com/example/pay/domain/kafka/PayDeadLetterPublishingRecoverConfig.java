package com.example.pay.domain.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;

@Slf4j
@Configuration
public class PayDeadLetterPublishingRecoverConfig {

    @Bean
    public DeadLetterPublishingRecoverer payDeadLetterPublishingRecoverer(KafkaTemplate template) {
        return new DeadLetterPublishingRecoverer(template,
                (consumerRecord, ex) -> {
                    log.error("=== pay-test-error, consumerRecord: {}", consumerRecord);
                    return new TopicPartition("pay-test-topic-DLT", consumerRecord.partition());
                });
    }
}
