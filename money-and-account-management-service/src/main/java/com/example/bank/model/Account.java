package com.example.bank.model;

import com.example.bank.constant.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false)
    private Long balance;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    @Column(name = "account_number", nullable = false, unique = true, columnDefinition = "CHAR(10)")
    private String accountNumber;

    public void subtractMoney(Long money) {
        this.balance -= money;
    }

    public void addMoney(Long money) {
        this.balance += money;
    }

}
