package com.example.bank.controller;

import com.example.bank.api.DepositingApi;
import com.example.bank.api.DrawMoneyApi;
import com.example.bank.api.ExchangeApi;
import com.example.bank.request.DepositingRequest;
import com.example.bank.request.DrawMoneyRequest;
import com.example.bank.request.ExchangeRequest;
import com.example.bank.response.DepositingResponse;
import com.example.bank.response.DrawMoneyResponse;
import com.example.bank.response.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private DrawMoneyApi drawMoneyApi;

    @Autowired
    private DepositingApi depositingApi;

    @Autowired
    private ExchangeApi exchangeApi;

    @PostMapping("/draw-money/{sourceAccountId}/{money}")
    public DrawMoneyResponse drawMoney(@PathVariable Long sourceAccountId, @PathVariable Float money) {
        DrawMoneyRequest drawMoneyRequest = new DrawMoneyRequest(sourceAccountId, money);
        return drawMoneyApi.execute(drawMoneyRequest);
    }

    @PostMapping("/deposit/{destinationAccountId}/{money}")
    public DepositingResponse deposit(@PathVariable Long destinationAccountId, @PathVariable Float money) {
        DepositingRequest depositingRequest = new DepositingRequest(destinationAccountId, money);
        return depositingApi.execute(depositingRequest);
    }

    @PostMapping("/exchange/{sourceAccountId}/{destinationAccountId}/{money}")
    public ExchangeResponse exchange(@PathVariable Long sourceAccountId,
                                     @PathVariable Long destinationAccountId,
                                     @PathVariable Float money) throws ExecutionException, InterruptedException {
        ExchangeRequest exchangeRequest = new ExchangeRequest(sourceAccountId, destinationAccountId, money);
        return exchangeApi.execute(exchangeRequest);
    }


}
