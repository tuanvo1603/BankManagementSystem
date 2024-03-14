package com.example.bank.api;

import com.example.bank.exception.StatusCode;
import com.example.bank.model.Transaction;
import com.example.bank.request.TransactionRequest;
import com.example.bank.response.TransactionResponse;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class TransactionApi extends CommonApi<TransactionResponse, TransactionRequest>{

    private static final String TRANSACTION_SUCCESSFULLY_NOTIFY = "Get transaction successfully.";
    public static final Integer PAGE_SIZE = 10;

    @Autowired
    private TransactionService transactionService;

    @Override
    public TransactionResponse execute(TransactionRequest request) throws ExecutionException, InterruptedException {

        Page<Transaction> transactions = transactionService.getAllTransactionOfAnUser(request.getUserId(),
                request.getPageNumber(), PAGE_SIZE);

        TransactionResponse transactionResponse = new TransactionResponse(StatusCode.SUCCESS.getCode(),
                TRANSACTION_SUCCESSFULLY_NOTIFY);
        transactionResponse.setTransaction(transactions);

        return transactionResponse;
    }
}
