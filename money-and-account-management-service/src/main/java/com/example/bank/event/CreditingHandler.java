package com.example.bank.event;

import com.example.bank.dto.CreditResponseMessage;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreditingHandler {

    @Autowired
    private AccountRepository accountRepository;

    @RetryableTopic(backoff = @Backoff(delay = 2000L, multiplier = 2))
    @KafkaListener(topics = "credit", groupId = "account_group")
    @Transactional
    public void handleCrediting(ConsumerRecord<String, CreditResponseMessage> record) {
        System.out.println(record.value().toString());
        Account account = accountRepository
                .findByAccountNumber(record.value().getAccountNumber());
        if(account == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        account.addMoney(record.value().getMoney());
        accountRepository.save(account);
    }

    @KafkaListener(topics = "credit-dlt", groupId = "account_group")
    public void handleDLT(ConsumerRecord<String, CreditResponseMessage> record) {
        //Todo
    }
}
