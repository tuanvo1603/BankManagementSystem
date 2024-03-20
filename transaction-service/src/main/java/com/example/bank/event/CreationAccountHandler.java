package com.example.bank.event;

import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreationAccountHandler {

    @Autowired
    private AccountRepository accountRepository;

//    @KafkaListener(topics = "createdAccount", groupId = "account_group")
//    @Transactional
//    public void handleCreationAccount(ConsumerRecord<String, CreatedAccountMessage> record) {
//        Account account = new Account()
//    }
}
