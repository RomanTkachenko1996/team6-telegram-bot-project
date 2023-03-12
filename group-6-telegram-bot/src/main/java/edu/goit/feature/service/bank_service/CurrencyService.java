package edu.goit.feature.service.bank_service;

import edu.goit.feature.currency_dto.CurrencyRateDto;
import edu.goit.feature.enums.Currency;

import java.util.List;

public interface CurrencyService {
    List<CurrencyRateDto> getCurrencyRates();
}
