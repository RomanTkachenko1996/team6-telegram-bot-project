package edu.goit.feature.currency_dto;

import edu.goit.feature.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PrivatDto {
    private Currency ccy;
    private BigDecimal buy;
    private BigDecimal sale;
}
