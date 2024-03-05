package com.example.moneyandaccountmanagementservice.controller;

import com.example.moneyandaccountmanagementservice.api.AccountCreationApi;
import com.example.moneyandaccountmanagementservice.api.AccountDetailFetchingApi;
import com.example.moneyandaccountmanagementservice.model.Account;
import com.example.moneyandaccountmanagementservice.request.AccountCreationRequest;
import com.example.moneyandaccountmanagementservice.request.AccountDetailFetchingRequest;
import com.example.moneyandaccountmanagementservice.response.AccountCreationResponse;
import com.example.moneyandaccountmanagementservice.response.AccountDetailFetchingResponse;
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
