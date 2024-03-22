package com.example.bank.request;

import com.example.bank.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateAccountRequest extends ApiRequest{

    private Account account;
}
