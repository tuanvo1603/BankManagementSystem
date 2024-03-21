package com.example.bank.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Topic {

    CREATED_ACCOUNT("created_topic"),

    CREDIT("credit_topic"),

    DEBIT("debit_topic");

    private final String topic;
}
