package com.example.bank.response;

import com.example.bank.model.Account;

import java.util.List;

public class AllAccountFetchingResponse extends ApiResponse {

    private List<Account> accounts;

    public AllAccountFetchingResponse(int code, String message) {
        super(code, message);
    }

    public AllAccountFetchingResponse(List<Account> accounts) {
        super();
        this.accounts = accounts;
    }
}
