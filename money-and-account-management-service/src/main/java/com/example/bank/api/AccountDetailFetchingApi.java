package com.example.bank.api;

import com.example.bank.model.Account;
import com.example.bank.request.AccountDetailFetchingRequest;
import com.example.bank.response.AccountDetailFetchingResponse;
import com.example.bank.service.AccountService;
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
