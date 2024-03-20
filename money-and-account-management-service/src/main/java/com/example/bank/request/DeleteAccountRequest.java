package com.example.bank.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class DeleteAccountRequest extends ApiRequest{

    private String accountNumber;
}
