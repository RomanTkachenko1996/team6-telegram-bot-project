package edu.goit.serviceNbu;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CurrencyRateNBUDto {
    private Integer r030;
    private String txt;
    private BigDecimal rate;
    private Currency cc;
    private String exchangedate;
}