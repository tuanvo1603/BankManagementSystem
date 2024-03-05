package com.example.moneyandaccountmanagementservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_MONEY_INPUT(1000, "Money is less than 0."),

    ACCOUNT_NOT_FOUND(1001, "Account has not been registered."),

    NOT_ENOUGH_MONEY_IN_ACCOUNT(1002, "There is not enough money in account.");

    private final int code;

    private final String message;

}
