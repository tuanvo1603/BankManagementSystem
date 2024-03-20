//package com.example.bank;
//
//import com.example.bank.constant.AccountType;
//import com.example.bank.model.Account;
//import com.example.bank.service.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Date;
//import java.time.LocalDate;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//public class TestCustomerService {
//
//    @Autowired
//    private AccountService accountService;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void testCreateAccount() {
//        // Create a sample account
//        Account account = new Account();
//        account.setUserId(3L);
//        account.setAccountType(AccountType.SAVINGS);
//        account.setCreateAt(Date.valueOf(LocalDate.now()));
//        account.setAccountNumber("010203");
//        // Call the method to be tested
//        System.out.println(account);
//        Account createdAccount = accountService.createAccount(account);
//        System.out.println(createdAccount);
//
//        // Assert that the returned account is not null
//        assertNotNull(createdAccount);
//
//        // You can add more assertions to verify the saved account data or use
//        // additional integration tests to validate database interactions further.
//    }
//}
