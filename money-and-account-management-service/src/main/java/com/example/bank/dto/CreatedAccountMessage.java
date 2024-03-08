package com.example.bank.dto;

import com.example.bank.constant.AccountType;
import lombok.Data;

@Data
public class CreatedAccountMessage {

    private Long accountId;

    private AccountType accountType;

    private Float balance;
}
