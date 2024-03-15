package com.example.bank.api;

import com.example.bank.request.DestinationAccountRequest;
import com.example.bank.response.UserInfoResponse;

public class FetchingDestinationUserApi extends CommonApi<UserInfoResponse, DestinationAccountRequest>{
    @Override
    public UserInfoResponse execute(DestinationAccountRequest request) {
        return null;
    }
}
