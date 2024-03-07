package com.example.bank.service;

import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public static final Float INITIAL_BALANCE = 0f;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DateService dateService;

    public void increaseBalance(Long accountId, Float money){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.setBalance(account.getBalance() + money);
        accountRepository.save(account);
    }

    private void isEnoughMoneyInBalance(Account account, Float money) {
        if(account.getBalance() < money)
            throw new AppException(ErrorCode.NOT_ENOUGH_MONEY_IN_ACCOUNT);
        //or publish an event to message queue
    }

    public void decreaseBalance(Long accountId, Float money){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        isEnoughMoneyInBalance(account, money);
        account.setBalance(account.getBalance() - money);
        accountRepository.save(account);
    }

}
