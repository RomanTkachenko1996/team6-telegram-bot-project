package main.java.edu.goit.feature.service.bank_service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class CurrencyMono {
    private static Map<Integer, MonoCurrencies> codeCurr = Map.of(
            980, MonoCurrencies.UAH,
            840, MonoCurrencies.USD,
            978, MonoCurrencies.EUR
    );
    public  List<CurrencyRateDto> getCurrencyRates() {
        try {

            String response = Jsoup.connect("https://api.monobank.ua/bank/currency").ignoreContentType(true)
                    .get()
                    .body()
                    .text();
            List<CurrencyRateMonoResponceDto> responceDtos = convert(response);
            return responceDtos.stream()
                    .filter(item -> codeCurr.containsKey(item.getCurrencyCodeA())
                            && codeCurr.containsKey(item.getCurrencyCodeB())
                            && item.getCurrencyCodeB().equals(980)
                    )
                    .map(item -> new CurrencyRateDto(
                            codeCurr.get(item.getCurrencyCodeA()),
                            item.getRateBuy(),
                            item.getRateSell(),
                            BankName.MONOBANK
                    ))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<CurrencyRateMonoResponceDto> convert(String responce) {
        Type type = TypeToken.getParameterized(List.class, CurrencyRateMonoResponceDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(responce, type);
    }
}