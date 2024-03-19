package com.example.bank.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionRequest extends ApiRequest{
    @NotEmpty(message = "INVALID_USER_ID")
    private Long userId;
    private Integer pageNumber;
}
