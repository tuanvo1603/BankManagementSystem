package com.example.bank.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetailFetchingRequest extends ApiRequest {

    @NotNull(message = "INVALID_ACCOUNT_ID")
    private Long accountId;
}
