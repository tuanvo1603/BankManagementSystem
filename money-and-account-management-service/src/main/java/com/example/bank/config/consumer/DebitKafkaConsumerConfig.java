package com.example.bank.config.consumer;

import com.example.bank.constant.GroupId;
import com.example.bank.dto.DebitResponseMessage;
import com.example.bank.model.Account;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class DebitKafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, DebitResponseMessage> debitConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GroupId.ACCOUNT_GROUP.getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); // Using ErrorHandlingDeserializer for value
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DebitResponseMessage> debitKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DebitResponseMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(debitConsumerFactory());
//        factory.getContainerProperties().setErrorHandler(new SeekToCurrentErrorHandler()); // Error handling
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE); // Acknowledgment mode
//        factory.getContainerProperties().setEosMode(EOSMode.BETA); // Enable exactly-once semantics (optional)
        return factory;
    }
}
