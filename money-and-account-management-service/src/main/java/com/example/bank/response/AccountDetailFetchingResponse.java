package com.example.bank.response;

import com.example.bank.model.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetailFetchingResponse extends ApiResponse{

    private Account account;

    public AccountDetailFetchingResponse(int code, String message) {
        super(code, message);
    }

    public AccountDetailFetchingResponse(Account account) {
        super();
        this.account = account;
    }
}
