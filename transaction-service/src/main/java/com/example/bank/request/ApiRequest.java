package com.example.bank.request;

import com.example.bank.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRequest {

    private Token token;

    private String apiName;
}
