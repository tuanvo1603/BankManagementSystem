package com.example.bank.dto.consumer;

import com.example.bank.constant.AccountType;
import lombok.Data;

@Data
public class CreatedAccountMessage {

    private Long accountId;

    private AccountType accountType;

    private Float balance;
}
