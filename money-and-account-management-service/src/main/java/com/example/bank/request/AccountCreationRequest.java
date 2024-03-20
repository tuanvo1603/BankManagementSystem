package com.example.bank.request;

import com.example.bank.model.Account;
import com.example.bank.token.Token;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class AccountCreationRequest extends ApiRequest{

    @NotEmpty(message = "ACCOUNT_NOT_FILLED")
    private Account account;

    public AccountCreationRequest(Account account, Token token) {
        this.account = account;
        this.token = token;
    }
}
