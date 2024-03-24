package com.example.bank.api;

import com.example.bank.exception.StatusCode;
import com.example.bank.request.DrawMoneyRequest;
import com.example.bank.response.DrawMoneyResponse;
import com.example.bank.utils.DateService;
import com.example.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawMoneyApi extends CommonApi<DrawMoneyResponse, DrawMoneyRequest>{

    private static final String DRAW_MONEY_SUCCESS_NOTIFY = "Draw money successfully";
    private final TransactionService transactionService;

    @Override
    public DrawMoneyResponse execute(DrawMoneyRequest request) {
        transactionService.deduct(request.getSourceAccountNumber(), request.getMoney());
        return new DrawMoneyResponse(StatusCode.SUCCESS.getCode(), DRAW_MONEY_SUCCESS_NOTIFY);
    }
}
