package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequest extends ApiRequest{

    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private String destinationAccountNumber;

    @NotEmpty(message = "INVALID_MONEY_INPUT")
    private Float money;

    public DepositRequest(String destinationAccountNumber, Float money) {
        this.destinationAccountNumber = destinationAccountNumber;
        this.money = money;
    }
}