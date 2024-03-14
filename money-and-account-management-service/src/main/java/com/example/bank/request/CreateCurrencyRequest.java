package com.example.bank.request;

import com.example.bank.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreateCurrencyRequest extends ApiRequest {

    private Currency currency;
}
