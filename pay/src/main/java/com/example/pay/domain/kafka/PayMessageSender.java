package com.example.pay.domain.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class PayMessageSender {

    private final KafkaTemplate payKafkaTemplate;
    private final ObjectMapper objectMapper;

    public PayMessageSender(KafkaTemplate payKafkaTemplate, ObjectMapper objectMapper) {
        this.payKafkaTemplate = payKafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(PayMessage payMessage) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(payMessage);
        log.info("send message : {}", message);
        ListenableFuture<SendResult<String, Object>> future = payKafkaTemplate.send("pay-test-topic","test-key", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
                log.error("Message Exception:{}", ex);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                ProducerRecord<String, Object> producerRecord = result.getProducerRecord();
                RecordMetadata recordMetadata = result.getRecordMetadata();
                log.info("topic: {},partition: {}, offset: {}",
                        recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                log.info("topic: {}, header: {}, key: {}, partition: {}",
                        producerRecord.topic(), producerRecord.headers(), producerRecord.key(), producerRecord.partition());
            }
        });
    }
}
