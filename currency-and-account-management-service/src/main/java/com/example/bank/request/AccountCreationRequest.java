package com.example.bank.request;

import com.example.bank.model.Account;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
public class AccountCreationRequest extends ApiRequest{

    @NotEmpty(message = "ACCOUNT_NOT_FILLED")
    private Account account;

    public AccountCreationRequest(Account account, String token) {
        this.account = account;
        this.token = token;
    }
}
