package edu.goit.feature.service.serviceNbu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
public class CurrencyNBUService implements CurrencyRetrievalService {
    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public List<CurrencyRateDto> getCurrencyRates() throws IOException {
        String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
        List<CurrencyRateNBUDto> responseDtos = convertResponseToList(response);
        return responseDtos.stream()
                .filter(item -> item.getCc() != null)
                .map(item -> new CurrencyRateDto(item.getCc(), null, item.getRate()))
                .collect(Collectors.toList());
    }

    private List<CurrencyRateNBUDto> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, CurrencyRateNBUDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
