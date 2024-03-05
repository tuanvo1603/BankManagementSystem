package com.example.moneyandaccountmanagementservice.api;

import com.example.moneyandaccountmanagementservice.request.ApiRequest;
import com.example.moneyandaccountmanagementservice.response.ApiResponse;


public abstract class CommonApi<T extends ApiResponse, U extends ApiRequest> {
    public abstract T execute(U request);
}
