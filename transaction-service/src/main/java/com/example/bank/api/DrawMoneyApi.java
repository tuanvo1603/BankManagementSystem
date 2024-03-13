package com.example.bank.api;

import com.example.bank.constant.TransactionType;
import com.example.bank.exception.StatusCode;
import com.example.bank.model.Transaction;
import com.example.bank.request.DrawMoneyRequest;
import com.example.bank.response.DrawMoneyResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.DateService;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DrawMoneyApi extends CommonApi<DrawMoneyResponse, DrawMoneyRequest>{

    private static final String DRAW_MONEY_SUCCESS_NOTIFY = "Draw money successfully";

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DateService dateService;

    @Override
    @Transactional
    public DrawMoneyResponse execute(DrawMoneyRequest request) {
        accountService.debit(request.getSourceAccountId(), request.getMoney());
        Transaction transaction = Transaction.builder()
                .sourceAccountId(request.getSourceAccountId())
                .transactionType(TransactionType.DRAW_MONEY)
                .transactionDate(dateService.getCurrentDate())
                .amount(request.getMoney())
                .build();
        transactionService.createTransaction(transaction);

        return new DrawMoneyResponse(StatusCode.SUCCESS.getCode(), DRAW_MONEY_SUCCESS_NOTIFY);
    }
}
