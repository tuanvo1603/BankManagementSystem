package com.example.bank.event;

import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreditingHandler {

    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(topics = "credit", groupId = "account_group")
    @Transactional
    public void handleCrediting(ConsumerRecord<String, Account> record) throws JsonProcessingException {
        System.out.println(record.value().toString());
        Account account = accountRepository
                .findById(record.value().getAccountId())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.setBalance(record.value().getBalance());
        accountRepository.save(account);
    }
}