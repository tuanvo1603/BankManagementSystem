package com.example.bank.request;

import com.example.bank.token.Token;
import lombok.Data;

@Data
public class ApiRequest {

    private Token token;

    private String apiName;
}
