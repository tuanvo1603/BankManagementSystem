package com.example.bank.service;

import com.example.bank.constant.Topic;
import com.example.bank.dto.CreatedAccountMessage;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.token.Token;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    private static final Long INITIAL_BALANCE_VALUE = 0L;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DateService dateService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, CreatedAccountMessage> createdAccountKafkaTemplate;

    @CircuitBreaker(name = "CHECK_EXISTING_USER_BREAKER", fallbackMethod = "checkExistingUserFallBack")
    private boolean checkExistenceOfUser(Long userId, Token token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getToken());
        HttpEntity<Long> entity = new HttpEntity<>(userId, headers);
        Boolean isExistedUser = restTemplate.exchange("http://localhost:8000/v1/internal/exist-user/", HttpMethod.GET, entity, Boolean.class).getBody();

        return Boolean.TRUE.equals(isExistedUser);
    }

    private void checkExistingUserFallBack(Throwable throwable) {
        throw new AppException(ErrorCode.SERVER_OVER_LOADING);
    }

    private void validateAccount(Account account, Token token) {
        if (!checkExistenceOfUser(account.getUserId(), token)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        if (accountRepository.existsAccountByAccountNumberEquals(account.getAccountNumber())) {
            throw new AppException(ErrorCode.ACCOUNT_ALREADY_EXISTED);
        }
    }

    @Transactional
    public Account createAccount(Account account, Token token) {
        this.validateAccount(account, token);
        if (account.getBalance() == null) {
            account.setBalance(INITIAL_BALANCE_VALUE);
        }
        account.setCreateAt(dateService.getCurrentDate());
        Account savedAccount = accountRepository.save(account);
        CreatedAccountMessage createdAccountMessage = CreatedAccountMessage.builder()
                .accountId(savedAccount.getAccountId())
                .accountType(savedAccount.getAccountType())
                .balance(savedAccount.getBalance())
                .accountNumber(savedAccount.getAccountNumber())
                .build();
        createdAccountKafkaTemplate.send(Topic.CREATED_ACCOUNT.getTopic(), createdAccountMessage);
        return savedAccount;
    }

    public void deleteAccount(String accountNumber) {
        accountRepository.deleteAccountByAccountNumber(accountNumber);
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
