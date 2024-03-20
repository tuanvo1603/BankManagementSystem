package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetailFetchingRequest extends ApiRequest {

    @NotEmpty(message = "INVALID_ACCOUNT_NUMBER")
    private String accountNumber;
}
