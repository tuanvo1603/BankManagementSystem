package com.example.bank.request;

import com.example.bank.model.Account;
import com.example.bank.token.Token;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCreationRequest extends ApiRequest{

    @NotEmpty(message = "ACCOUNT_NOT_FILLED")
    private Account account;

    private Token token;
}
