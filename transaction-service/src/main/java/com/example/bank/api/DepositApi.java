package com.example.bank.api;

import com.example.bank.constant.TransactionType;
import com.example.bank.exception.StatusCode;
import com.example.bank.model.Transaction;
import com.example.bank.request.DepositRequest;
import com.example.bank.response.DepositResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.DateService;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DepositApi extends CommonApi<DepositResponse, DepositRequest>{

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DateService dateService;

    private static final String DEPOSITING_SUCCESSFULLY_NOTIFY = "Deposit successfully.";

    @Override
    @Transactional
    public DepositResponse execute(DepositRequest request) {
        accountService.credit(request.getDestinationAccountId(), request.getMoney());
        Transaction transaction = Transaction.builder()
                .transactionDate(dateService.getCurrentDate())
                .transactionType(TransactionType.CREDIT)
                .destinationAccountId(request.getDestinationAccountId())
                .amount(request.getMoney())
                .build();
        transactionService.createTransaction(transaction);

        return new DepositResponse(StatusCode.SUCCESS.getCode(), DEPOSITING_SUCCESSFULLY_NOTIFY);
    }
}
