package com.example.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CreditResponseDTO implements Serializable {

    private Long accountId;

    private Float money;
}
