package com.example.bank.service;

import com.example.bank.constant.Topic;
import com.example.bank.dto.CreditResponseDTO;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DateService dateService;

    @Autowired
    private KafkaTemplate<String, Account> creditKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Account> debitKafkaTemplate;

    @Transactional
    public void credit(Long destinationAccountId, Float money) {
        Account account = accountRepository.findById(destinationAccountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.setBalance(account.getBalance() + money);
        Account changedAccount = accountRepository.save(account);
        creditKafkaTemplate.send(Topic.CREDIT.getTopic(), changedAccount);
    }

    private void isBalanceSufficient(Account account, Float money) {
        if(account.getBalance() < money)
            throw new AppException(ErrorCode.NOT_ENOUGH_MONEY_IN_ACCOUNT);
    }

    @Transactional
    public void debit(Long sourceAccountId, Float money){
        Account account = accountRepository.findById(sourceAccountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        isBalanceSufficient(account, money);
        account.setBalance(account.getBalance() - money);
        Account changedAccount = accountRepository.save(account);
        debitKafkaTemplate.send(Topic.DEBIT.getTopic(), changedAccount);
    }


}
