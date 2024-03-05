package com.example.moneyandaccountmanagementservice.model;

import com.example.moneyandaccountmanagementservice.constant.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
