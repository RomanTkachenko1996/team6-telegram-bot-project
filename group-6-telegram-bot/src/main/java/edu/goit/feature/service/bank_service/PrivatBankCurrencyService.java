package edu.goit.feature.service.bank_service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.goit.feature.currency_dto.CurrencyRateDto;
import edu.goit.feature.currency_dto.PrivatDto;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import static edu.goit.feature.enums.BankName.*;

public class PrivatBankCurrencyService implements CurrencyService {

    private final static String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=11";

    @Override
    public List<CurrencyRateDto> getCurrencyRates() {
        try {
            String response = Jsoup.connect(URL)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

            List<PrivatDto> responseDtos = convertResponseToList(response);
            return responseDtos.stream()
                    .map(dto -> new CurrencyRateDto(dto.getCcy(), dto.getSale(), dto.getBuy(), PRIVATBANK))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PrivatDto> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, PrivatDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);

    }
}