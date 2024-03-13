package com.example.bank.api;

import com.example.bank.constant.TransactionType;
import com.example.bank.exception.StatusCode;
import com.example.bank.model.Transaction;
import com.example.bank.request.TransferRequest;
import com.example.bank.response.TransferResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.DateService;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class TransferApi extends CommonApi<TransferResponse, TransferRequest>{

    private static final String EXCHANGE_SUCCESSFULLY_NOTIFY = "Exchange successfully.";

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DateService dateService;

    @Override
    public TransferResponse execute(TransferRequest request) {
        accountService.credit(request.getDestinationAccountNumber(), request.getMoney());
        accountService.debit(request.getSourceAccountNumber(), request.getMoney());
        Transaction transaction = Transaction.builder()
                .sourceAccountNumber(request.getSourceAccountNumber())
                .destinationAccountNumber(request.getDestinationAccountNumber())
                .amount(request.getMoney())
                .transactionType(TransactionType.TRANSFER)
                .transactionDate(dateService.getCurrentDate())
                .build();
        transactionService.createTransaction(transaction);

        return new TransferResponse(StatusCode.SUCCESS.getCode(), EXCHANGE_SUCCESSFULLY_NOTIFY);
    }
}
