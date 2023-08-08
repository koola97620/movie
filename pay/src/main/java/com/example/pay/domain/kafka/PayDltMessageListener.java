
package com.example.pay.domain.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayDltMessageListener {

    private final ObjectMapper objectMapper;

    public PayDltMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics="pay-test-topic-DLT", groupId="test-dlt-topic-group-1", containerFactory = "payDltKafkaListenerContainerFactory")
    public void onMessage(ConsumerRecord<String, String>  data) {
        PayMessage message = getMessage(data);
        log.info("DLT-message:{}", message);
    }

//    @KafkaListener(topics="test-topic", groupId="test-topic-group-1", containerFactory = "payKafkaListenerContainerFactory")
//    public void onMessage(ConsumerRecord<String, String> data, Acknowledgment ack) {
//        PayMessage message = getMessage(data);
//        log.info("message:{}", message);
//        if (message.isErrorMessage()) {
//            log.info("error message");
//            ack.nack(Duration.ofSeconds(1L));
//        }
//        ack.acknowledge();
//    }

    private PayMessage getMessage(ConsumerRecord<String, String> data) {
        try {
            return objectMapper.readValue(data.value(), PayMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
