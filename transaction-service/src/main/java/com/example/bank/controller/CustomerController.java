package com.example.bank.controller;

import com.example.bank.api.*;
import com.example.bank.model.Account;
import com.example.bank.request.*;
import com.example.bank.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final DrawMoneyApi drawMoneyApi;
    private final DepositApi depositApi;
    private final TransferApi transferApi;
    private final CreateAccountApi createAccountApi;
    private final DeleteAccountApi deleteAccountApi;
    private final TransactionApi transactionApi;

    @PostMapping("/draw-money")
    public DrawMoneyResponse drawMoney(@RequestParam String sourceAccountNumber, @RequestParam BigDecimal money) {
        DrawMoneyRequest drawMoneyRequest = new DrawMoneyRequest(sourceAccountNumber, money);
        return drawMoneyApi.execute(drawMoneyRequest);
    }

    @PostMapping("/deposit")
    public DepositResponse deposit(@RequestParam String destinationAccountNumber, @RequestParam BigDecimal money) {
        DepositRequest depositRequest = new DepositRequest(destinationAccountNumber, money);
        return depositApi.execute(depositRequest);
    }

    @PostMapping("/transfer")
    public TransferResponse transfer(@RequestParam String sourceAccountNumber,
                                     @RequestParam String destinationAccountNumber,
                                     @RequestParam BigDecimal money){
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
//        throw new RuntimeException();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(account);
        return createAccountApi.execute(createAccountRequest);
    }

    @DeleteMapping("/delete-account")
    public DeleteAccountResponse deleteAccount(@RequestParam("accountNumber") String accountNumber) {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(accountNumber);
        return deleteAccountApi.execute(deleteAccountRequest);
    }
}
