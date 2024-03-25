package com.example.bank.api;

import com.example.bank.exception.StatusCode;
import com.example.bank.request.TransferRequest;
import com.example.bank.response.TransferResponse;
import com.example.bank.utils.DateService;
import com.example.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferApi extends CommonApi<TransferResponse, TransferRequest>{

    private static final String EXCHANGE_SUCCESSFULLY_NOTIFY = "Transfer successfully.";
    private final TransactionService transactionService;

    @Override
    public TransferResponse execute(TransferRequest request) {
        for(int i = 0; i < 500; i++) {
            transactionService.transfer(request.getSourceAccountNumber(), request.getDestinationAccountNumber(), request.getMoney());
        }
        return new TransferResponse(StatusCode.SUCCESS.getCode(), EXCHANGE_SUCCESSFULLY_NOTIFY);
    }
}
