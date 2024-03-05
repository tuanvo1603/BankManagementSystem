package com.example.moneyandaccountmanagementservice.service.currency;

import com.example.moneyandaccountmanagementservice.exception.AppException;
import com.example.moneyandaccountmanagementservice.exception.ErrorCode;
import com.example.moneyandaccountmanagementservice.model.Currency;
import com.example.moneyandaccountmanagementservice.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency importCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public List<Currency> getCurrenciesList() {
        return currencyRepository.findAll();
    }

    public void deleteCurrency(Long currencyCode) {
        currencyRepository.deleteById(currencyCode);
    }

    public void updateCurrency(Currency currency) {
        if(!currencyRepository.existsById(currency.getCurrencyCode())) {
            throw new AppException(ErrorCode.CURRENCY_NOT_FOUND);
        }

        currencyRepository.save(currency);
    }
}
