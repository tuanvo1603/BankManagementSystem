package com.example.bank.service;

import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void isBalanceSufficient(Account account, Long money) {
        if (account.getBalance() < money) throw new AppException(ErrorCode.NOT_ENOUGH_MONEY_IN_ACCOUNT);
    }

    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        return account;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(String accountNumber) {
        accountRepository.deleteByAccountNumberEquals(accountNumber);
    }
}
