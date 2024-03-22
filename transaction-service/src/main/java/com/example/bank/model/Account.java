package com.example.bank.model;

import com.example.bank.constant.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "account")
public class Account {

    @Id
    private Long accountId;

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
        this.balance = this.balance + money;
    }

    public void addMoney(Long money) {
        this.balance += money;
    }

}

