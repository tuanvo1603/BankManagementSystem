package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DrawMoneyRequest extends ApiRequest{

    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private String sourceAccountNumber;

    @NotEmpty(message = "INVALID_MONEY_INPUT")
    private BigDecimal money;
}
