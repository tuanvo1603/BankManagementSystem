package com.example.bank.dto;

import com.example.bank.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedAccountMessage {

    private Long accountId;

    private AccountType accountType;

    private Long balance;

    private String accountNumber;
}
