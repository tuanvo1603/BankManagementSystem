package com.example.bank.request;

import com.example.bank.token.Token;
import lombok.Data;

@Data
public class ApiRequest {

    protected Token token;

    protected String apiName;
}
