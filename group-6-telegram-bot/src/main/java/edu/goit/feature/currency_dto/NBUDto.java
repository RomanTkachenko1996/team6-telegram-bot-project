package edu.goit.feature.currency_dto;

import edu.goit.feature.enums.Currency;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class NBUDto {
    private BigDecimal rate;
    private Currency cc;
}