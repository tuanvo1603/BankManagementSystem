package com.example.bank.service.account;

import com.example.bank.dto.AccountDTO;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public static final Float INITIAL_BALANCE = 0f;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DateService dateService;

    @Autowired
    private KafkaTemplate<String, Account> createdAccountKafkaTemplate;

    public Account createAccount(Account account) {

        account.setBalance(INITIAL_BALANCE);
        account.setCreateAt(dateService.getCurrentDate());
        Account savedAccount = accountRepository.save(account);
//        AccountDTO accountDTO = new AccountDTO();
//        accountDTO.setAccountId(savedAccount.getAccountId());
//        accountDTO.setAccountType(savedAccount.getAccountType());
//        accountDTO.setAccountNumber(savedAccount.getAccountNumber());
//        accountDTO.setUserId(savedAccount.getUserId());
//        accountDTO.setCreateAt(savedAccount.getCreateAt());
        createdAccountKafkaTemplate.send("createdAccount", savedAccount);
        return savedAccount;
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

    public void updateAccount(Account account) {
        if(!accountRepository.existsById(account.getAccountId())) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }

        accountRepository.save(account);
    }
}
