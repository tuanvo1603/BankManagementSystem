package com.example.bank.dto.producer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CreditResponseMessage implements Serializable {

    private Long accountId;

    private Float money;
}
