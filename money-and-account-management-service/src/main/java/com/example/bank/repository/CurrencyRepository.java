package com.example.bank.repository;

import com.example.bank.constant.CurrencySymbol;
import com.example.bank.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findCurrencyBySymbolEquals(CurrencySymbol currencySymbol);

    void deleteBySymbolEquals(CurrencySymbol currencySymbol);
}
