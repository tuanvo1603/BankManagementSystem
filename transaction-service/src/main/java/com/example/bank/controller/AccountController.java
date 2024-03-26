package com.example.bank.controller;

import com.example.bank.api.CreateAccountApi;
import com.example.bank.api.DeleteAccountApi;
import com.example.bank.model.Account;
import com.example.bank.request.CreateAccountRequest;
import com.example.bank.request.DeleteAccountRequest;
import com.example.bank.response.CreateAccountResponse;
import com.example.bank.response.DeleteAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountApi createAccountApi;
    private final DeleteAccountApi deleteAccountApi;
    @PostMapping("/create")
    public CreateAccountResponse createAccount(@RequestBody Account account) {
//        throw new RuntimeException();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(account);
        return createAccountApi.execute(createAccountRequest);
    }

    @DeleteMapping("/delete")
    public DeleteAccountResponse deleteAccount(@RequestParam("accountNumber") String accountNumber) {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(accountNumber);
        return deleteAccountApi.execute(deleteAccountRequest);
    }

}