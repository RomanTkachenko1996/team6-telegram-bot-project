package edu.goit.serviceNbu;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class CurrencyRateNBUDto {
    private BigDecimal rate;
    private Currency cc;
}