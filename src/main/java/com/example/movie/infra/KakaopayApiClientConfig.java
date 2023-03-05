package com.example.movie.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Request;
import feign.Retryer;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class KakaopayApiClientConfig {

    private final String url;

    public KakaopayApiClientConfig(@Value("${kakaopay.host}") String url) {
        this.url = url;
    }

    @Bean
    public KakaopayApiClient kakaopayApiClient() {
        CircuitBreaker cb = CircuitBreaker.of("kakaopayApiClientCB",
                CircuitBreakerConfig.custom()
                        .failureRateThreshold(50)
                        .slidingWindow(20, 20, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                        .permittedNumberOfCallsInHalfOpenState(10)
                        .waitDurationInOpenState(Duration.ofSeconds(10L))
                        .build()
        );

        FeignDecorators decorators = FeignDecorators.builder()
                .withCircuitBreaker(cb)
                .build();

        ObjectMapper objectMapper = (new ObjectMapper())
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModules(new JavaTimeModule());

        return Resilience4jFeign.builder(decorators)
                .client(new OkHttpClient())
                .encoder(new FormEncoder())
                .decoder(new JacksonDecoder(objectMapper))
                .options(new Request.Options(3000, TimeUnit.MILLISECONDS, 10000, TimeUnit.MILLISECONDS, true))
                // 0.1초 간격으로 시작해 최대 3초의 간격으로 3번 재시도
                .retryer(new Retryer.Default(100, SECONDS.toMillis(3), 3))
                .logger(new Slf4jLogger(KakaopayApiClient.class))
                .target(KakaopayApiClient.class, url);
    }
}
