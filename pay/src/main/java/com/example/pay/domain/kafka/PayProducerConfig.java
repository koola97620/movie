package com.example.pay.domain.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PayProducerConfig {

    private Map<String, Object> payProducerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "211.184.188.34:29093,211.184.188.33:29093,211.184.188.35:29093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    @Bean
    public ProducerFactory<String, Object> payProducerFactory() {
        return new DefaultKafkaProducerFactory<>(payProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, Object> payKafkaTemplate() {
        return new KafkaTemplate<>(payProducerFactory());
    }
}
