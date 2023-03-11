package main.java.edu.goit.feature.service.bank_service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateDto {
    private MonoCurrencies currency;
    private BigDecimal buyRate;
    private BigDecimal sellRate;
    private BankName name;

    public CurrencyRateDto(MonoCurrencies currency, BigDecimal buyRate, BankName NBU) {
        currency = currency;
        buyRate = buyRate;
        name = NBU;
        buyRate=BigDecimal.valueOf(0);
    }
}
