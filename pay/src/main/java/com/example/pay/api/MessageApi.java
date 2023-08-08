package com.example.pay.api;

import com.example.pay.domain.kafka.PayMessage;
import com.example.pay.domain.kafka.PayMessageSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageApi {

    private final PayMessageSender producer;

    public MessageApi(PayMessageSender producer) {
        this.producer = producer;
    }

    @PostMapping("/test/send/message")
    public String test(@RequestBody PayMessage message) throws JsonProcessingException {
        producer.send(message);

        return "success";
    }
}
