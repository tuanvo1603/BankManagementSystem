package com.example.bank.api;

import com.example.bank.request.ApiRequest;
import com.example.bank.response.ApiResponse;


public abstract class CommonApi<T extends ApiResponse, U extends ApiRequest> {
    public abstract T execute(U request);
}
