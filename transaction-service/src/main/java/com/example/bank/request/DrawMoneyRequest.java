package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrawMoneyRequest extends ApiRequest{
    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private String sourceAccountNumber;

    @NotEmpty(message = "INVALID_MONEY_INPUT")
    private Float money;

    public DrawMoneyRequest(String sourceAccountNumber, Float money) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.money = money;
    }
}
