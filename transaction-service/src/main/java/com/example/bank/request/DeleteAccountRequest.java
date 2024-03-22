package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteAccountRequest extends ApiRequest{

    @NotEmpty(message = "INVALID_ACCOUNT_NUMBER")
    private String accountNumber;
}
