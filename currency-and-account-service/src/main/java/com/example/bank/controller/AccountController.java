package com.example.bank.controller;

import com.example.bank.api.*;
import com.example.bank.dto.CreateAccountDTO;
import com.example.bank.request.*;
import com.example.bank.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountCreationApi accountCreationApi;
    private final AccountDetailFetchingApi accountDetailFetchingApi;
    private final UpdateAccountApi updateAccountApi;
    private final DeleteAccountApi deleteAccountApi;
    private final AllUserAccountFetchingApi allUserAccountFetchingApi;
    private final FetchingDestinationUserApi fetchingDestinationUserApi;

    @PostMapping("/create")
    public AccountCreationResponse createAccount(@RequestBody @Valid CreateAccountDTO createAccountDTO, @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        AccountCreationRequest accountCreationRequest = new AccountCreationRequest(createAccountDTO, token);
        return accountCreationApi.execute(accountCreationRequest);
    }

    @GetMapping("/detail/{accountNumber}")
    public AccountDetailFetchingResponse getAccountDetail(@PathVariable String accountNumber) {
        AccountDetailFetchingRequest accountDetailFetchingRequest = new AccountDetailFetchingRequest(accountNumber);
        return accountDetailFetchingApi.execute(accountDetailFetchingRequest);
    }

    @PutMapping("/update")
    public UpdateAccountResponse updateAccount(@RequestParam Long accountId, @RequestParam String newAccountNumber) {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(accountId, newAccountNumber);
        return updateAccountApi.execute(updateAccountRequest);
    }

    @DeleteMapping("/delete")
    public DeleteAccountResponse deleteAccount(@RequestParam String accountNumber, @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(accountNumber, token);
        return deleteAccountApi.execute(deleteAccountRequest);
    }

    @GetMapping("/get-all-user-account/{pageNumber}")
    public AllAccountFetchingResponse getAllUserAccount(@PathVariable Integer pageNumber){
        AllAccountFetchingRequest request = new AllAccountFetchingRequest();
        request.setPageNumber(pageNumber);
        return allUserAccountFetchingApi.execute(request);
    }

    @GetMapping("/get-username-account/{destinationAccountNumber}")
    public UserInfoResponse getDestinationUserInfo(@PathVariable String destinationAccountNumber) {
        DestinationAccountRequest request = new DestinationAccountRequest(destinationAccountNumber);
        return fetchingDestinationUserApi.execute(request);
    }
}
