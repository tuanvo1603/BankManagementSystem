package com.example.bank.api;

import com.example.bank.exception.StatusCode;
import com.example.bank.model.Account;
import com.example.bank.request.AllAccountFetchingRequest;
import com.example.bank.response.AllAccountFetchingResponse;
import com.example.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AllUserAccountFetchingApi extends CommonApi<AllAccountFetchingResponse, AllAccountFetchingRequest>{

    public static final Integer PAGE_SIZE_ACCOUNT = 4;
    private final AccountService accountService;

    @Override
    public AllAccountFetchingResponse execute(AllAccountFetchingRequest request) {

        Integer pageNumber = request.getPageNumber();

        Page<Account> accountPage = accountService.getAllUserAccounts(pageNumber,PAGE_SIZE_ACCOUNT);
        List<Account> accounts = accountPage.getContent();
        Integer pageSize = accountPage.getSize();
        Integer totalPage = accountPage.getTotalPages();

        AllAccountFetchingResponse allAccountFetchingResponse = new
                AllAccountFetchingResponse(StatusCode.SUCCESS.getCode(), null, accounts);
        allAccountFetchingResponse.setTotalPage(totalPage);
        allAccountFetchingResponse.setPageSize(pageSize);
        return allAccountFetchingResponse;
    }
}
