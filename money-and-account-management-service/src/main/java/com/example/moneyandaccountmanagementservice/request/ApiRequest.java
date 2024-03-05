package com.example.moneyandaccountmanagementservice.request;

import com.example.moneyandaccountmanagementservice.token.Token;
import lombok.Data;

@Data
public class ApiRequest {

    private Token token;

    private String apiName;
}
