package com.example.moneyandaccountmanagementservice.response;

import com.example.moneyandaccountmanagementservice.model.Account;
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
