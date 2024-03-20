package com.example.bank.dto;

import com.example.bank.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedAccountMessage {

    private Long accountId;

    private AccountType accountType;

    private Long balance;

    private String accountNumber;
}
