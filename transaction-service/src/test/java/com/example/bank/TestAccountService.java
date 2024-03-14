package com.example.bank;

import com.example.bank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class TestAccountService {

    @Autowired
    private AccountService accountService;

    @Test
    public void exchangeMoney() throws ExecutionException, InterruptedException {

//        List<Account> accounts = accountService.getAllAccountByUser(3L);

        System.out.println(accounts);

        assertNotNull(accounts);
    }
}
