package com.example.bank.api;

import com.example.bank.exception.StatusCode;
import com.example.bank.request.AccountCreationRequest;
import com.example.bank.response.AccountCreationResponse;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCreationApi extends CommonApi<AccountCreationResponse, AccountCreationRequest>{

    private static final String CREATION_SUCCESSFULLY = "Create account successfully.";

    @Autowired
    private AccountService accountService;

    @Override
    public AccountCreationResponse execute(AccountCreationRequest request) {
        accountService.createAccount(request.getAccount(), request.getToken());

        return new AccountCreationResponse(StatusCode.SUCCESS.getCode(), CREATION_SUCCESSFULLY);
    }
}
