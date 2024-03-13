package com.example.bank.event;

import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DebitHandler {

    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(topics = "debit", groupId = "account_group")
    public void handleDebiting(ConsumerRecord<String, Account> record) {
        Account account = accountRepository
                .findById(record.value().getAccountId())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.setBalance(record.value().getBalance());
        accountRepository.save(account);
        System.out.println(record.value().toString());
    }
}
