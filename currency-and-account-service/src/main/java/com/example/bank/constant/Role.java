package com.example.bank.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    CUSTOMER("CUSTOMER"),

    ADMIN("ADMIN");

    private final String role;
}
