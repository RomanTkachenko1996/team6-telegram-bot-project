package edu.goit.button;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Currency {
   private String ccy;
   private String base_ccy;
   private BigDecimal buy;
   private BigDecimal sale;

   public enum ALL_CCY {
      USD,
      EUR
   }
}
