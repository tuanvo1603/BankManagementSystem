package com.example.bank.controller;

import com.example.bank.api.*;
import com.example.bank.model.Account;
import com.example.bank.request.*;
import com.example.bank.response.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private DrawMoneyApi drawMoneyApi;

    @Autowired
    private DepositApi depositApi;

    @Autowired
    private TransferApi transferApi;

    @Autowired
    private CreateAccountApi createAccountApi;

    @Autowired
    private DeleteAccountApi deleteAccountApi;

    @Autowired
    private TransactionApi transactionApi;

    @PostMapping("/draw-money/{sourceAccountNumber}/{money}")
    public DrawMoneyResponse drawMoney(@PathVariable String sourceAccountNumber, @PathVariable Long money) {
        DrawMoneyRequest drawMoneyRequest = new DrawMoneyRequest(sourceAccountNumber, money);
        return drawMoneyApi.execute(drawMoneyRequest);
    }

    @PostMapping("/deposit/{destinationAccountNumber}/{money}")
    public DepositResponse deposit(@PathVariable String destinationAccountNumber, @PathVariable Long money) {
        DepositRequest depositRequest = new DepositRequest(destinationAccountNumber, money);
        return depositApi.execute(depositRequest);
    }

    @PostMapping("/transfer/{sourceAccountNumber}/{destinationAccountNumber}/{money}")
    public TransferResponse transfer(@PathVariable String sourceAccountNumber,
                                     @PathVariable String destinationAccountNumber,
                                     @PathVariable Long money){
        TransferRequest transferRequest = new TransferRequest(sourceAccountNumber, destinationAccountNumber, money);
        return transferApi.execute(transferRequest);
    }

    @GetMapping("/get-transaction/{pageNumber}")
    public TransactionResponse getTransaction(@PathVariable Integer pageNumber, @AuthenticationPrincipal Jwt jwt) {
        Long userId = (Long) jwt.getClaims()
                .values()
                .stream()
                .toList()
                .get(7);
        TransactionRequest transactionRequest = new TransactionRequest(userId, pageNumber);
        return transactionApi.execute(transactionRequest);
    }

    @PostMapping("/create-account")
    public CreateAccountResponse createAccount(@RequestBody Account account) {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(account);
        return createAccountApi.execute(createAccountRequest);
    }

    @DeleteMapping("/delete-account")
    public DeleteAccountResponse deleteAccount(@RequestBody String accountNumber) {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(accountNumber);
        return deleteAccountApi.execute(deleteAccountRequest);
    }
}
