package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeRequest extends ApiRequest{

    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private Long sourceAccountId;

    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private Long destinationAccountId;

    @Positive(message = "INVALID_MONEY_INPUT")
    private Float money;
}
