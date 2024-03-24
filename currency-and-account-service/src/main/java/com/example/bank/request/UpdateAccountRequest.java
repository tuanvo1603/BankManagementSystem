package com.example.bank.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateAccountRequest extends ApiRequest{

    private Long accountId;

    private String newAccountNumber;
}
