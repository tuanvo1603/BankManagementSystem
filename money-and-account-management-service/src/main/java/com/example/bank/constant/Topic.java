package com.example.bank.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum Topic {

    CREDIT("credit_topic"),

    DEBIT("debit_topic"),

    CREATED_ACCOUNT("created_account");


    private final String topic;
}
