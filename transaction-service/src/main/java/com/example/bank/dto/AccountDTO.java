package com.example.bank.dto;

import com.example.bank.constant.AccountType;
import lombok.Data;

import java.sql.Date;

@Data
public class AccountDTO {

    private Long accountId;

    private Long userId;

    private AccountType accountType;

    private Float balance;

    private Date createAt;

    private String accountNumber;
}
