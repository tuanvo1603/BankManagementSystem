package com.example.bank.api;

import com.example.bank.constant.TransactionType;
import com.example.bank.exception.ErrorCode;
import com.example.bank.exception.StatusCode;
import com.example.bank.model.Transaction;
import com.example.bank.request.DepositingRequest;
import com.example.bank.response.DepositingResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.DateService;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DepositingApi extends CommonApi<DepositingResponse, DepositingRequest>{

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DateService dateService;

    private static final String DEPOSITING_SUCCESSFULLY_NOTIFY = "Deposit successfully.";

    @Override
    @Transactional
    public DepositingResponse execute(DepositingRequest request) {
        accountService.credit(request.getDestinationAccountId(), request.getMoney());
        Transaction transaction = Transaction.builder()
                .transactionDate(dateService.getCurrentDate())
                .transactionType(TransactionType.CREDIT)
                .destinationAccountId(request.getDestinationAccountId())
                .sourceAccountId(null)
                .amount(request.getMoney())
                .build();
        System.out.println(transaction);
        transactionService.createTransaction(transaction);

        return new DepositingResponse(StatusCode.SUCCESS.getCode(), DEPOSITING_SUCCESSFULLY_NOTIFY);
    }
}
