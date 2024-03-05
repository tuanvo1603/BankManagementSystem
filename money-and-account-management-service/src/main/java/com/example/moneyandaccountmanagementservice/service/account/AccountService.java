package com.example.moneyandaccountmanagementservice.service.account;

import com.example.moneyandaccountmanagementservice.exception.AppException;
import com.example.moneyandaccountmanagementservice.exception.ErrorCode;
import com.example.moneyandaccountmanagementservice.model.Account;
import com.example.moneyandaccountmanagementservice.repository.AccountRepository;
import com.example.moneyandaccountmanagementservice.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public static final Float INITIAL_BALANCE = 0f;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DateService dateService;

    //Transaction roll back for roll back if there is not userId like input in system
    public Account createAccount(Account account) {
        // Call api to check existence of user in system

        account.setBalance(INITIAL_BALANCE);
        account.setCreateAt(dateService.getCurrentDate());
        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public Float getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        return account.getBalance();
    }

    public Account getAccountDetail(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
    }

    public void increaseBalance(Long accountId, Float money){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        account.setBalance(account.getBalance() + money);
        accountRepository.save(account);
    }

    private void isEnoughMoneyInBalance(Account account, Float money) {
        if(account.getBalance() < money)
            throw new AppException(ErrorCode.NOT_ENOUGH_MONEY_IN_ACCOUNT);
    }

    public void decreaseBalance(Long accountId, Float money){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        isEnoughMoneyInBalance(account, money);
        account.setBalance(account.getBalance() - money);
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        if(!accountRepository.existsById(account.getAccountId())) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }

        accountRepository.save(account);
    }
}
