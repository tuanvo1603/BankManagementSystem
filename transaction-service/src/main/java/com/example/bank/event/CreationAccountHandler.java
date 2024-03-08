package com.example.bank.event;

import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreationAccountHandler {

    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(topics = "createdAccount", groupId = "account_group")
    @Transactional
    public void handleCreationAccount(ConsumerRecord<String, Account> record) {
        accountRepository.save(record.value());
    }
}
