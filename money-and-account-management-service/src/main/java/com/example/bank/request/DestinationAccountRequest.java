package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DestinationAccountRequest extends ApiRequest{
    @NotEmpty(message = "INVALID_ACCOUNT_ID")
    private Long destinationAccountId;

    public DestinationAccountRequest(Long destinationAccountId){
        this.destinationAccountId = destinationAccountId;
    }
}
