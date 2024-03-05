package com.example.moneyandaccountmanagementservice.service.account;

import com.example.moneyandaccountmanagementservice.model.Account;
import com.example.moneyandaccountmanagementservice.repository.AccountRepository;
import com.example.moneyandaccountmanagementservice.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void getBalance(Long accountId) {
        accountRepository.findById(a)
    }
}
