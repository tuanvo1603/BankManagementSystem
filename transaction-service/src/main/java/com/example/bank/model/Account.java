package com.example.bank.model;

import com.example.bank.constant.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false)
    private Float balance;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    @Column(name = "account_number", nullable = false, unique = true, length = 17)
    private String accountNumber;

}

