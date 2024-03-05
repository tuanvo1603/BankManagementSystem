package com.example.moneyandaccountmanagementservice.api;

import com.example.moneyandaccountmanagementservice.model.Account;
import com.example.moneyandaccountmanagementservice.request.AccountDetailFetchingRequest;
import com.example.moneyandaccountmanagementservice.response.AccountDetailFetchingResponse;
import com.example.moneyandaccountmanagementservice.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailFetchingApi extends CommonApi<AccountDetailFetchingResponse, AccountDetailFetchingRequest>{

    @Autowired
    private AccountService accountService;

    @Override
    public AccountDetailFetchingResponse execute(AccountDetailFetchingRequest request) {
        Account account = accountService.getAccountDetail(request.getAccountId());

        return new AccountDetailFetchingResponse(account);
    }
}
