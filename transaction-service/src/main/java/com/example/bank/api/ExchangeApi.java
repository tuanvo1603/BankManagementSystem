package com.example.bank.api;

import com.example.bank.constant.TransactionType;
import com.example.bank.exception.StatusCode;
import com.example.bank.model.Transaction;
import com.example.bank.request.ExchangeRequest;
import com.example.bank.response.ExchangeResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.DateService;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ExchangeApi extends CommonApi<ExchangeResponse, ExchangeRequest>{

    private static final String EXCHANGE_SUCCESSFULLY_NOTIFY = "Exchange successfully.";

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DateService dateService;

    @Override
    public ExchangeResponse execute(ExchangeRequest request) throws ExecutionException, InterruptedException {
        accountService.credit(request.getDestinationAccountId(), request.getMoney());
        accountService.debit(request.getSourceAccountId(), request.getMoney());
        Transaction transaction = Transaction.builder()
                .sourceAccountId(request.getSourceAccountId())
                .destinationAccountId(request.getDestinationAccountId())
                .amount(request.getMoney())
                .transactionType(TransactionType.TRANSFER)
                .transactionDate(dateService.getCurrentDate())
                .build();
        transactionService.createTransaction(transaction);

        return new ExchangeResponse(StatusCode.SUCCESS.getCode(), EXCHANGE_SUCCESSFULLY_NOTIFY);
    }
}
