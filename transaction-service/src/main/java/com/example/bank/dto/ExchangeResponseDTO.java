package com.example.bank.dto;

import java.io.Serializable;

public class ExchangeResponseDTO implements Serializable {

    private Long sourceAccountId;

    private Long destinationAccountId;

    private Float money;
}
