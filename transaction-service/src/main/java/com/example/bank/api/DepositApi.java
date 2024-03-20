package com.example.bank.api;

import com.example.bank.exception.StatusCode;
import com.example.bank.request.DepositRequest;
import com.example.bank.response.DepositResponse;
import com.example.bank.utils.DateService;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositApi extends CommonApi<DepositResponse, DepositRequest>{

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DateService dateService;

    private static final String DEPOSITING_SUCCESSFULLY_NOTIFY = "Deposit successfully.";

    @Override
    public DepositResponse execute(DepositRequest request) {
        transactionService.credit(request.getDestinationAccountNumber(), request.getMoney(), true);
        return new DepositResponse(StatusCode.SUCCESS.getCode(), DEPOSITING_SUCCESSFULLY_NOTIFY);
    }
}
