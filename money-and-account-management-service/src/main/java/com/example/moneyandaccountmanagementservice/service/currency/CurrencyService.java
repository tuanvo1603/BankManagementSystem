package com.example.moneyandaccountmanagementservice.service.currency;

import com.example.moneyandaccountmanagementservice.model.Currency;
import com.example.moneyandaccountmanagementservice.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getCurrenciesList() {
        return currencyRepository.findAll();
    }
}
