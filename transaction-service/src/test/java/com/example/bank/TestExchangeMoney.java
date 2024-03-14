package com.example.bank;

import com.example.bank.api.ExchangeApi;
import com.example.bank.request.ExchangeRequest;
import com.example.bank.response.ExchangeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class TestExchangeMoney {

    @Autowired
    private ExchangeApi exchangeApi;

    @Test
    public void exchangeMoney() throws ExecutionException, InterruptedException {
        ExchangeRequest exchangeRequest = new ExchangeRequest(202l,2l,999f);
        ExchangeResponse exchangeResponse = exchangeApi.execute(exchangeRequest);

        assertNotNull(exchangeResponse);
    }
}
