package com.example.bank;

import com.example.bank.model.Transaction;
import com.example.bank.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class TestTransactionService {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void getAllTransactionByUsers() throws ExecutionException, InterruptedException {

        Page<Transaction> accounts = transactionService.getAllTransactionOfAnUser(3L,0,10);

        System.out.println(accounts);

        assertNotNull(accounts);
    }
}
