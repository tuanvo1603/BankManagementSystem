package com.example.bank.dto;

import com.example.bank.constant.AccountType;
import com.example.bank.validation.AccountNumberValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateAccountDTO {

    @NotEmpty(message = "User Id can not be blank.")
    private Long userId;

    @NotEmpty(message = "Account Type can not be blank.")
    private AccountType accountType;

    @PositiveOrZero
    private BigDecimal balance;

    @AccountNumberValidation
    @NotEmpty(message = "Account Number can not be blank.")
    private String accountNumber;
}
