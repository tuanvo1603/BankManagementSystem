package com.example.bank.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Topic {

    INCREASE_BALANCE("increase_balance"),

    CREATED_ACCOUNT("created_account");


    private final String topic;
}
