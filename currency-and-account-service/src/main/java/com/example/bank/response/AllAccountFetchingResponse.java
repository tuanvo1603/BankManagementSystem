package com.example.bank.response;

import com.example.bank.model.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllAccountFetchingResponse extends ApiResponse {

    public List<Account> accounts;

    public Integer pageSize;

    public Integer totalPage;

    public AllAccountFetchingResponse(int code, String message) {
        super(code, message);
    }

    public AllAccountFetchingResponse(int code, String message, List<Account> accounts) {
        super(code, message);
        this.accounts = accounts;
    }
}
