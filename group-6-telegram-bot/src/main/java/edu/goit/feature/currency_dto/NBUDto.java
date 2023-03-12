package edu.goit.feature.currency_dto;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class NBUDto {
    private BigDecimal rate;
    private Currency cc;
}