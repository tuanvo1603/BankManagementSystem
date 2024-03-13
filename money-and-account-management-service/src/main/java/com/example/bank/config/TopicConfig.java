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
    @Qualifier("credit")
    public NewTopic creditTopic() {
        return TopicBuilder.name(Topic.CREDIT.getTopic()).build();
    }

    @Bean
    @Qualifier("created_account")
    public NewTopic createAccountTopic() {
        return TopicBuilder.name(Topic.CREATED_ACCOUNT.getTopic()).build();
    }
}
