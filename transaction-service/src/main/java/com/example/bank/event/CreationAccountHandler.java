package com.example.bank.event;

import com.example.bank.dto.AccountDTO;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CreationAccountHandler {

    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(topics = "createdAccount", groupId = "account_group")
    public void handleCreationAccount(ConsumerRecord<String, Account> record) {
        System.out.println(record.value().toString());
        accountRepository.save(record.value());
//        return accountRepository.save(record.value());
    }
}
