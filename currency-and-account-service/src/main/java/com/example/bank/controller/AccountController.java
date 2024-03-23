package com.example.bank.controller;

import com.example.bank.api.AccountCreationApi;
import com.example.bank.api.AccountDetailFetchingApi;
import com.example.bank.api.DeleteAccountApi;
import com.example.bank.api.UpdateAccountApi;
import com.example.bank.model.Account;
import com.example.bank.request.AccountCreationRequest;
import com.example.bank.request.AccountDetailFetchingRequest;
import com.example.bank.request.DeleteAccountRequest;
import com.example.bank.request.UpdateAccountRequest;
import com.example.bank.response.AccountCreationResponse;
import com.example.bank.response.AccountDetailFetchingResponse;
import com.example.bank.response.DeleteAccountResponse;
import com.example.bank.response.UpdateAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountCreationApi accountCreationApi;
    private final AccountDetailFetchingApi accountDetailFetchingApi;
    private final UpdateAccountApi updateAccountApi;
    private final DeleteAccountApi deleteAccountApi;

    @PostMapping("/create")
    public AccountCreationResponse createAccount(@RequestBody Account account) {
        String token = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue();
        AccountCreationRequest accountCreationRequest = new AccountCreationRequest(account, token);
        return accountCreationApi.execute(accountCreationRequest);
    }

    @GetMapping("/detail/{accountNumber}")
    public AccountDetailFetchingResponse getAccountDetail(@PathVariable String accountNumber) {
        AccountDetailFetchingRequest accountDetailFetchingRequest = new AccountDetailFetchingRequest(accountNumber);
        return accountDetailFetchingApi.execute(accountDetailFetchingRequest);
    }

    @PutMapping("/update")
    public UpdateAccountResponse updateAccount(@RequestBody Account account) {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(account);
        return updateAccountApi.execute(updateAccountRequest);
    }

    @DeleteMapping("/delete/{accountNumber}")
    public DeleteAccountResponse deleteAccount(@PathVariable String accountNumber) {
        String token = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue();
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(accountNumber, token);
        return deleteAccountApi.execute(deleteAccountRequest);
    }
}
