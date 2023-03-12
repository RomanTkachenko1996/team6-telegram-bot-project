package edu.goit.feature.currency_dto;

import edu.goit.feature.enums.Currency;
import edu.goit.feature.enums.BankName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateDto {
    private Currency currency;
    private BigDecimal buyRate;
    private BigDecimal sellRate;
    private BankName name;

    // for nbu as the don't have buy rate
    public CurrencyRateDto(Currency currency, BigDecimal sellRate, BankName NBU) {
        this.currency = currency;
        this.sellRate = sellRate;
        name = NBU;
        buyRate = BigDecimal.valueOf(0);
    }
}
