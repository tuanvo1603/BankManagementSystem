package com.example.bank;

import com.example.bank.api.AllUserAccountFetchingApi;
import com.example.bank.api.FetchingDestinationUserApi;
import com.example.bank.model.Account;
import com.example.bank.request.AllAccountFetchingRequest;
import com.example.bank.request.DestinationAccountRequest;
import com.example.bank.response.AllAccountFetchingResponse;
import com.example.bank.response.UserInfoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
@Rollback(value = false)
public class TestAccountApi {

    @Autowired
    FetchingDestinationUserApi fetchingDestinationUserApi;

    @Autowired
    private AllUserAccountFetchingApi allUserAccountFetchingApi;

    @Test
    public void testAllUserAccount(){
        AllAccountFetchingRequest request = new AllAccountFetchingRequest();
        AllAccountFetchingResponse response = allUserAccountFetchingApi.execute(request);
        List<Account> accounts = response.accounts;
        assertTrue(accounts.size() > 1);
    }

    @Test
    public void getUserInfo() throws JsonProcessingException {
        String destinationAccountId = "010203";
        DestinationAccountRequest request = new DestinationAccountRequest(destinationAccountId);
        UserInfoResponse userInfoResponse = fetchingDestinationUserApi.execute(request);
        assertNotNull(userInfoResponse);
    }

}
