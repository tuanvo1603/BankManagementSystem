package com.example.bank.config;

import com.example.bank.constant.Topic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic creditTopic() {
        return TopicBuilder.name(Topic.CREDIT.getTopic()).build();
    }

    @Bean
    public NewTopic debitTopic() {
        return TopicBuilder.name(Topic.DEBIT.getTopic()).build();
    }
}
