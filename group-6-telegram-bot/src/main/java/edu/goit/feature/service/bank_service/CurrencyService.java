package edu.goit.feature.service.bank_service;

import edu.goit.feature.currency_dto.CurrencyRateDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyRateDto> getCurrencyRates();
}
