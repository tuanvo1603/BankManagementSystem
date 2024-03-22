package com.example.bank.api;

import com.example.bank.model.Account;
import com.example.bank.request.AllAccountFetchingRequest;
import com.example.bank.response.AllAccountFetchingResponse;
import com.example.bank.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllUserAccountFetchingApi extends CommonApi<AllAccountFetchingResponse, AllAccountFetchingRequest>{

    @Autowired
    private AccountService accountService;

    @Override
    public AllAccountFetchingResponse execute(AllAccountFetchingRequest request) {

        Long userId = 3L;
        List<Account> accounts = accountService.getAllUserAccount(userId);
        AllAccountFetchingResponse response = new AllAccountFetchingResponse(accounts);

        return response;
    }
}
