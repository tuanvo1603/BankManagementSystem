package com.example.bank.controller;

import com.example.bank.api.AccountCreationApi;
import com.example.bank.api.AccountDetailFetchingApi;
import com.example.bank.model.Account;
import com.example.bank.request.AccountCreationRequest;
import com.example.bank.request.AccountDetailFetchingRequest;
import com.example.bank.response.AccountCreationResponse;
import com.example.bank.response.AccountDetailFetchingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/v1")
public class CustomerController {

    @Autowired
    private AccountCreationApi accountCreationApi;

    @Autowired
    private AccountDetailFetchingApi accountDetailFetchingApi;

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
}
