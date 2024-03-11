package com.example.bank.controller;

import com.example.bank.api.DepositingApi;
import com.example.bank.api.DrawMoneyApi;
import com.example.bank.api.ExchangeApi;
import com.example.bank.api.TransactionApi;
import com.example.bank.request.DepositingRequest;
import com.example.bank.request.DrawMoneyRequest;
import com.example.bank.request.ExchangeRequest;
import com.example.bank.request.TransactionRequest;
import com.example.bank.response.DepositingResponse;
import com.example.bank.response.DrawMoneyResponse;
import com.example.bank.response.ExchangeResponse;
import com.example.bank.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private TransactionApi transactionApi;

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

    //get all transaction of user
    @GetMapping("/transaction/{pageNumber}")
    public TransactionResponse getAllTransaction(@PathVariable Integer pageNumber) throws ExecutionException, InterruptedException {

        // get user Id by authen
        Long userId = 3L;

        TransactionRequest transactionRequest = new TransactionRequest(userId, pageNumber);
        return transactionApi.execute(transactionRequest);
    }


}
