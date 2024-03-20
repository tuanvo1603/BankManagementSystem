package com.example.bank.api;

import com.example.bank.exception.StatusCode;
import com.example.bank.request.TransferRequest;
import com.example.bank.response.TransferResponse;
import com.example.bank.utils.DateService;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferApi extends CommonApi<TransferResponse, TransferRequest>{

    private static final String EXCHANGE_SUCCESSFULLY_NOTIFY = "Exchange successfully.";

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DateService dateService;

    @Override
    public TransferResponse execute(TransferRequest request) {
        transactionService.transfer(request.getSourceAccountNumber(), request.getDestinationAccountNumber(), request.getMoney());
        return new TransferResponse(StatusCode.SUCCESS.getCode(), EXCHANGE_SUCCESSFULLY_NOTIFY);
    }
}
