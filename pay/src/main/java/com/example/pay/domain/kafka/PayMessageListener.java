package com.example.pay.domain.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class PayMessageListener {

    private final ObjectMapper objectMapper;

    public PayMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics="pay-test-topic", groupId="test-topic-group-1", containerFactory = "payKafkaListenerContainerFactory")
    public void onMessage(ConsumerRecord<String, String>  data) {
        PayMessage message = getMessage(data);
        log.info("message:{}", message);
        if (message.isErrorMessage()) {
            throw new RuntimeException("에러메시지 발생!");
        }
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
