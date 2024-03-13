package com.example.bank.service;

import com.example.bank.constant.Topic;
import com.example.bank.dto.CreditResponseMessage;
import com.example.bank.dto.DebitResponseMessage;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DateService dateService;

    @Autowired
    private KafkaTemplate<String, CreditResponseMessage> creditKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, DebitResponseMessage> debitKafkaTemplate;

    @Transactional
    public void credit(String destinationAccountNumber, Float money) {
        Account account = accountRepository.findByAccountNumber(destinationAccountNumber);
        if(destinationAccountNumber == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        account.addMoney(money);
        Account changedAccount = accountRepository.save(account);
        CreditResponseMessage creditResponseMessage = new CreditResponseMessage(changedAccount.getAccountNumber(), money);
        creditKafkaTemplate.send(Topic.CREDIT.getTopic(), creditResponseMessage);
    }

    private void isBalanceSufficient(Account account, Float money) {
        if(account.getBalance() < money)
            throw new AppException(ErrorCode.NOT_ENOUGH_MONEY_IN_ACCOUNT);
    }

    @Transactional
    public void debit(String sourceAccountNumber, Float money) {
        Account account = accountRepository.findByAccountNumber(sourceAccountNumber);
        if(sourceAccountNumber == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        isBalanceSufficient(account, money);
        account.subtractMoney(money);
        Account changedAccount = accountRepository.save(account);
        DebitResponseMessage debitResponseMessage = new DebitResponseMessage(changedAccount.getAccountNumber(), money);
        debitKafkaTemplate.send(Topic.DEBIT.getTopic(), debitResponseMessage);
    }

}
