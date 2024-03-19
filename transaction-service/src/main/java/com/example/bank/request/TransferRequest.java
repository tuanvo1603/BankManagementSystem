package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferRequest extends ApiRequest{

    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private String sourceAccountNumber;

    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private String destinationAccountNumber;

    @Positive(message = "INVALID_MONEY_INPUT")
    private Long money;
}
