package com.example.bank.api;

import com.example.bank.model.Currency;
import com.example.bank.request.FetchCurrencyRequest;
import com.example.bank.response.FetchCurrencyResponse;
import com.example.bank.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchCurrencyApi extends CommonApi<FetchCurrencyResponse, FetchCurrencyRequest>{

    @Autowired
    private CurrencyService currencyService;

    @Override
    public FetchCurrencyResponse execute(FetchCurrencyRequest request) {
        Currency currency = currencyService.getCurrency(request.getCurrencySymbol());
        return new FetchCurrencyResponse(currency);
    }
}
