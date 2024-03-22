package com.example.bank.service;

import com.example.bank.constant.Topic;
import com.example.bank.dto.CreatedAccountMessage;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class AccountService {

    private static final String USER_EXISTENCE_CHECKING_URL = "http://auth-service/v1/internal/exist-user";

    private static final String SYN_CREATE_ACCOUNT_TO_TRANSACTION_SERVICE_URL = "http://transaction-service/v1/customer/create-account";

    private static final String SYN_DELETE_ACCOUNT_TO_TRANSACTION_SERVICE_URL = "http://transaction-service/v1/customer/delete-account";

    private static final Long INITIAL_BALANCE_VALUE = 0L;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, CreatedAccountMessage> createdAccountKafkaTemplate;

    @CircuitBreaker(name = "CHECK_EXISTING_USER_BREAKER", fallbackMethod = "checkExistingUserFallBack")
    private boolean checkExistenceOfUser(Long userId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Long> entity = new HttpEntity<>(userId, headers);
        Boolean isExistedUser = restTemplate.exchange(USER_EXISTENCE_CHECKING_URL,
                HttpMethod.GET,
                entity,
                Boolean.class)
                .getBody();
        return Boolean.TRUE.equals(isExistedUser);
    }

    private void synCreateAccountToTransactionService(Account account, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Account> entity = new HttpEntity<>(account, headers);
        restTemplate.exchange(SYN_CREATE_ACCOUNT_TO_TRANSACTION_SERVICE_URL,
                HttpMethod.POST,
                entity,
                Void.class);
    }

    private void synDeleteAccountToTransactionService(String accountNumber, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(accountNumber, headers);
        restTemplate.exchange(SYN_DELETE_ACCOUNT_TO_TRANSACTION_SERVICE_URL,
                HttpMethod.DELETE,
                entity,
                Void.class);
    }

    private void checkExistingUserFallBack(Throwable throwable) {
        throw new AppException(ErrorCode.REQUEST_TIMEOUT);
    }

    private void validateAccountBeforeCreation(Account account, String token) {
        if (!checkExistenceOfUser(account.getUserId(), token)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        if (accountRepository.existsAccountByAccountNumberEquals(account.getAccountNumber())) {
            throw new AppException(ErrorCode.ACCOUNT_ALREADY_EXISTED);
        }
    }

    @Transactional
    public Account createAccount(Account account, String token) {
//        this.validateAccountBeforeCreation(account, token);
        if (account.getBalance() == null) {
            account.setBalance(INITIAL_BALANCE_VALUE);
        }
        account.setCreateAt(Date.valueOf(LocalDate.now()));
        Account createdAccount = accountRepository.save(account);
        this.synCreateAccountToTransactionService(createdAccount, token);
        CreatedAccountMessage createdAccountMessage = CreatedAccountMessage.builder()
                .accountId(createdAccount.getAccountId())
                .accountType(createdAccount.getAccountType())
                .balance(createdAccount.getBalance())
                .accountNumber(createdAccount.getAccountNumber())
                .build();
        createdAccountKafkaTemplate.send(Topic.CREATED_ACCOUNT.getTopic(), createdAccountMessage);
        return createdAccount;
    }

    @Transactional
    public void deleteAccount(String accountNumber, String token) {
        if(!accountRepository.existsAccountByAccountNumberEquals(accountNumber)) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        accountRepository.deleteAccountByAccountNumber(accountNumber);
        this.synDeleteAccountToTransactionService(accountNumber, token);
    }

    public Account getAccountDetail(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        return account;
    }

    public void updateAccount(Account account) {
        if (!accountRepository.existsById(account.getAccountId())) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        accountRepository.save(account);
    }
}
