package com.example.moneyandaccountmanagementservice.repository;

import com.example.moneyandaccountmanagementservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
