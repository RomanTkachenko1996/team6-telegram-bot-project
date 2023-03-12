package edu.goit.feature.service.bank_service;

import edu.goit.feature.enums.Currency;

public interface CurrencyService {
    double getRate(Currency currency);
}
