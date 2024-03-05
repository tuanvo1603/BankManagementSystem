package com.example.moneyandaccountmanagementservice.api;

import com.example.moneyandaccountmanagementservice.exception.StatusCode;
import com.example.moneyandaccountmanagementservice.request.AccountCreationRequest;
import com.example.moneyandaccountmanagementservice.response.AccountCreationResponse;
import com.example.moneyandaccountmanagementservice.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCreationApi extends CommonApi<AccountCreationResponse, AccountCreationRequest>{

    private static final String CREATION_SUCCESSFULLY = "Create account successfully.";

    @Autowired
    private AccountService accountService;

    @Override
    public AccountCreationResponse execute(AccountCreationRequest request) {
            accountService.createAccount(request.getAccount());

            return new AccountCreationResponse(StatusCode.SUCCESS.getCode(), CREATION_SUCCESSFULLY);
    }
}
