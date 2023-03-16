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

    public CurrencyRateDto(Currency cc, BigDecimal rate, BankName nbu) {
        currency = cc;
        sellRate = rate;
        name = nbu;
        buyRate = BigDecimal.valueOf(0);
    }
}
