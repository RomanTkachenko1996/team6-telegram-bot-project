package edu.goit.feature.currency_dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class MonoDto {
        private Integer currencyCodeA;
        private Integer currencyCodeB;
        private BigDecimal rateSell;
        private BigDecimal rateBuy;
    }

