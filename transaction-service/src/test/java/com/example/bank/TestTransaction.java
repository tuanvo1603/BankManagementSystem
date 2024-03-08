package com.example.bank;

import com.example.bank.api.DepositingApi;
import com.example.bank.api.DrawMoneyApi;
import com.example.bank.constant.AccountType;
import com.example.bank.model.Account;
import com.example.bank.request.DepositingRequest;
import com.example.bank.request.DrawMoneyRequest;
import com.example.bank.response.DepositingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.*;


import java.sql.Date;
import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class TestTransaction {

    @Autowired
    private DrawMoneyApi drawMoneyApi;
    @Autowired
    private DepositingApi depositingApi;

    @BeforeEach
    void setUp() {
    }

    @Test
    @RepeatedTest(1000)
    void testAddMoney() {

        DepositingRequest depositingRequest = new DepositingRequest(202L,100f);
        DepositingResponse depositingResponse = depositingApi.execute(depositingRequest);
        System.out.println(depositingResponse);


//        assertNotNull(depositingResponse);
        assertFalse(() -> {
            boolean b = depositingResponse.getCode() != 1;
            return b;
        });

    }

    @Test
    void testDrawMoney() {
        // Create a sample account
//        DrawMoneyRequest drawMoneyRequest = new DrawMoneyRequest()


        Account account = new Account();
//        account.setUserId(3L);
        account.setAccountType(AccountType.SAVINGS);
//        account.setCreateAt(Date.valueOf(LocalDate.now()));
//        account.setAccountNumber("12345678901234578");
        // Call the method to be tested
        System.out.println(account);
//        Account createdAccount = accountService.createAccount(account);
//        System.out.println(createdAccount);

        // Assert that the returned account is not null
//        assertNotNull(createdAccount);

        // You can add more assertions to verify the saved account data or use
        // additional integration tests to validate database interactions further.
    }
}
