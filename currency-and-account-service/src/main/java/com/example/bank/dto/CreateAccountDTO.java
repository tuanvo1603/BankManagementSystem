package com.example.bank.dto;

import com.example.bank.constant.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CreateAccountDTO {

//    @NotEmpty(message = "User Id can not be blank.")
    private Long userId;

//    @NotEmpty(message = "Account Type can not be blank.")
    private AccountType accountType;

//    @PositiveOrZero
    private BigDecimal balance;

//    @Pattern(regexp = "^[0-9]+$", message = "Account Number can not contain characters.")
//    @NotEmpty(message = "Account Number can not be blank.")
    private String accountNumber;
}
