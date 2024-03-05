package com.example.moneyandaccountmanagementservice.repository;

import com.example.moneyandaccountmanagementservice.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
