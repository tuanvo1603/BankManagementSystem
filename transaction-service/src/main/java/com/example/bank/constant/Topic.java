package com.example.bank.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Topic {

    CREATED_ACCOUNT("created_topic"),

    CREDIT("credit"),

    DEBIT("debit");

    private final String topic;
}
