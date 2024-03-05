package com.example.moneyandaccountmanagementservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_MONEY_INPUT(1000, "Money is less than 0."),

    ACCOUNT_NOT_FOUND(1001, "Account has not been registered."),

    NOT_ENOUGH_MONEY_IN_ACCOUNT(1002, "There is not enough money in account."),

    CURRENCY_NOT_FOUND(1003, "There is not this currency in system."),

    ACCOUNT_ALREADY_EXISTED(1004, "Account has been registered."),

    CURRENCY_ALREADY_EXISTED(1005, "Currency has been used."),

    ACCOUNT_NOT_FILLED(1006, "Please fulfill the account information."),

    INVALID_ACCOUNT_ID(1007, "Please fill the account Id.");

    private final int code;

    private final String message;

}
