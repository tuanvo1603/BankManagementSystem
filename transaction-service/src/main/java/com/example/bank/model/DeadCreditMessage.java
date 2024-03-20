package com.example.bank.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dead_credit_message")
@Data
public class DeadCreditMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "money")
    private Float money;
}
