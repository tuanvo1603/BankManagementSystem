package com.example.bank.controller;

import com.example.bank.api.AccountCreationApi;
import com.example.bank.api.AccountDetailFetchingApi;
import com.example.bank.api.AllUserAccountFetchingApi;
import com.example.bank.model.Account;
import com.example.bank.request.AccountCreationRequest;
import com.example.bank.request.AccountDetailFetchingRequest;
import com.example.bank.request.AllAccountFetchingRequest;
import com.example.bank.response.AccountCreationResponse;
import com.example.bank.response.AccountDetailFetchingResponse;
import com.example.bank.response.AllAccountFetchingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
public class AccountController {
    @Autowired
    private AccountCreationApi accountCreationApi;

    @Autowired
    private AccountDetailFetchingApi accountDetailFetchingApi;

    @Autowired
    private AllUserAccountFetchingApi allUserAccountFetchingApi;

    @PostMapping("/create-account")
    public AccountCreationResponse createAccount(@RequestBody Account account) {
        AccountCreationRequest accountCreationRequest = new AccountCreationRequest(account);
        return accountCreationApi.execute(accountCreationRequest);
    }

    @GetMapping("/get-account-detail/{accountId}")
    public AccountDetailFetchingResponse getAccountDetail(@PathVariable Long accountId) {
        AccountDetailFetchingRequest accountDetailFetchingRequest = new AccountDetailFetchingRequest(accountId);
        return accountDetailFetchingApi.execute(accountDetailFetchingRequest);
    }

    @GetMapping("/get-all-user-account")
    public AllAccountFetchingResponse getAllUserAccount(){
        AllAccountFetchingRequest request = new AllAccountFetchingRequest();
        return allUserAccountFetchingApi.execute(request);
    }
}
