package com.example.bank.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreditResponseMessage implements Serializable{

    private Long accountId;

    private Float money;
}
