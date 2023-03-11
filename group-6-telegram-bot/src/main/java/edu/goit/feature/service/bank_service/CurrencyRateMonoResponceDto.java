package main.java.edu.goit.feature.service.bank_service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CurrencyRateMonoResponceDto {
        private Integer currencyCodeA;
        private Integer currencyCodeB;
        private BigDecimal rateSell;
        private BigDecimal rateBuy;
    }

