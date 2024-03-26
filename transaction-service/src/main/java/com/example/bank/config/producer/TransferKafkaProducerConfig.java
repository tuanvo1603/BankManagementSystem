package com.example.bank.config.producer;

import com.example.bank.dto.TransferResponseMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TransferKafkaProducerConfig {

    @Bean
    public ProducerFactory<String, TransferResponseMessage> transferProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.ACKS_CONFIG, "-1");
//        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 30);
//        props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 400);
//        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 100);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "zstd");
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 200);

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, TransferResponseMessage> debitKafkaTemplate() {
        return new KafkaTemplate<>(transferProducerFactory());
    }
}
