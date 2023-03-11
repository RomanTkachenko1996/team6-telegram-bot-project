import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CurrencyRatePrivatResponseDto {
    private Currency ccy;
    private BigDecimal buy;
    private BigDecimal sale;
}
