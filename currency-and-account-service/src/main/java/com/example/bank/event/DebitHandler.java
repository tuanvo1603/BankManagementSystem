package com.example.bank.event;

import com.example.bank.dto.DebitResponseMessage;
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

    @KafkaListener(topics = "debit-topic", groupId = "account-group")
    public void handleDebiting(ConsumerRecord<String, DebitResponseMessage> record) {
        Account account = accountRepository
                .findByAccountNumber(record.value().getAccountNumber());
        if(account == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        account.subtractMoney(record.value().getMoney());
        accountRepository.save(account);
    }
}