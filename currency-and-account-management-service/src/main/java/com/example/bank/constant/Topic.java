package com.example.bank.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum Topic {

    CREDIT("credit-topic"),

    DEBIT("debit-topic"),

    CREATED_ACCOUNT("created-account");

    private final String topic;
}
