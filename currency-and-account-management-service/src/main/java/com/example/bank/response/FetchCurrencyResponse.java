package com.example.bank.response;

import com.example.bank.model.Currency;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FetchCurrencyResponse extends ApiResponse{

    private Currency currency;
}
