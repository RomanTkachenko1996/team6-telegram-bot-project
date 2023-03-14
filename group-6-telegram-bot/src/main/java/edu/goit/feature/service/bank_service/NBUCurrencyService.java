package edu.goit.feature.service.bank_service;

import com.google.gson.reflect.TypeToken;
import edu.goit.feature.Constants;
import edu.goit.feature.currency_dto.CurrencyRateDto;
import edu.goit.feature.currency_dto.NBUDto;
import edu.goit.feature.enums.BankName;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import static edu.goit.feature.enums.Currency.*;

public class NBUCurrencyService implements CurrencyService {
    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public List<CurrencyRateDto> getCurrencyRates() {
        try {
            String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
            List<NBUDto> responseDtos = convertResponseToList(response);
            return responseDtos.stream()
                    .filter(it->it.getCc()==EUR || it.getCc()==USD)
                    .map(dto -> new CurrencyRateDto(dto.getCc(), dto.getRate(),BankName.NBU))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<NBUDto> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, NBUDto.class).getType();
        return Constants.GSON.fromJson(response, type);
    }
}
