package com.example.bank.event;

import com.example.bank.dto.CreatedAccountMessage;
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
    public void handleCreationAccount(ConsumerRecord<String, CreatedAccountMessage> record) {
        Account account = new Account(record.value().getAccountId(), record.value().getAccountType(), record.value().getBalance());
        accountRepository.save(account);
    }
}
