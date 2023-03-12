package edu.goit.feature.service.bank_service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.goit.feature.currency_dto.CurrencyRateDto;
import edu.goit.feature.currency_dto.MonoDto;
import edu.goit.feature.enums.Currency;
import edu.goit.feature.enums.BankName;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.goit.feature.enums.Currency.*;
import static edu.goit.feature.enums.BankName.*;
public class MonobankCurrencyService implements CurrencyService {
    private static final Map<Integer, Currency> codeCurr = Map.of(
            980, UAH,
            840, USD,
            978, EUR
    );
    private static final String URL = "https://api.monobank.ua/bank/currency";
    @Override
    public  List<CurrencyRateDto> getCurrencyRates() {
        try {

            String response = Jsoup.connect(URL).ignoreContentType(true)
                    .get()
                    .body()
                    .text();
            List<MonoDto> responseDtos = convertResponseToList(response);
            return responseDtos.stream()
                    .filter(item -> codeCurr.containsKey(item.getCurrencyCodeA())
                            && codeCurr.containsKey(item.getCurrencyCodeB())
                            && item.getCurrencyCodeB().equals(980)
                    )
                    .map(item -> new CurrencyRateDto(
                            codeCurr.get(item.getCurrencyCodeA()),
                            item.getRateBuy(),
                            item.getRateSell(),
                            MONOBANK
                    ))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<MonoDto> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, MonoDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}