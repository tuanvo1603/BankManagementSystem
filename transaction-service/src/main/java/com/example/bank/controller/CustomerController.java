package com.example.bank.controller;

import com.example.bank.api.DepositApi;
import com.example.bank.api.DrawMoneyApi;
import com.example.bank.api.TransferApi;
import com.example.bank.request.DepositRequest;
import com.example.bank.request.DrawMoneyRequest;
import com.example.bank.request.TransferRequest;
import com.example.bank.response.DepositResponse;
import com.example.bank.response.DrawMoneyResponse;
import com.example.bank.response.TransferResponse;
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
    private DepositApi depositApi;

    @Autowired
    private TransferApi transferApi;

    @PostMapping("/draw-money/{sourceAccountNumber}/{money}")
    public DrawMoneyResponse drawMoney(@PathVariable String sourceAccountNumber, @PathVariable Float money) {
        DrawMoneyRequest drawMoneyRequest = new DrawMoneyRequest(sourceAccountNumber, money);
        return drawMoneyApi.execute(drawMoneyRequest);
    }

    @PostMapping("/deposit/{destinationAccountNumber}/{money}")
    public DepositResponse deposit(@PathVariable String destinationAccountNumber, @PathVariable Float money) {
        DepositRequest depositRequest = new DepositRequest(destinationAccountNumber, money);
        return depositApi.execute(depositRequest);
    }

    @PostMapping("/transfer/{sourceAccountNumber}/{destinationAccountNumber}/{money}")
    public TransferResponse transfer(@PathVariable String sourceAccountNumber,
                                     @PathVariable String destinationAccountNumber,
                                     @PathVariable Float money){
        TransferRequest transferRequest = new TransferRequest(sourceAccountNumber, destinationAccountNumber, money);
        return transferApi.execute(transferRequest);
    }
}
