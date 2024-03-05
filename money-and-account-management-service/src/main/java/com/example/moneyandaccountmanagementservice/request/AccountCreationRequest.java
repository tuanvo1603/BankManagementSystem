package com.example.moneyandaccountmanagementservice.request;

import com.example.moneyandaccountmanagementservice.model.Account;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCreationRequest extends ApiRequest{

    @NotNull(message = "ACCOUNT_NOT_FILLED")
    private Account account;
}
