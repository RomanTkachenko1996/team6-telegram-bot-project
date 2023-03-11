import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyRetrievalPrivatService implements CurrencyRetrievalService{

        private final static String  URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=11";
        // приєднуємось до посилання публічного API приватБанк
        @Override
        public List<CurrencyRateDto> getCurrencyRates(){
            try {
                String response = Jsoup.connect(URL)
                        .ignoreContentType(true) //jsoup працює тилькі з xml, щоб працювало зи всіма вибираємо ignore
                        .get()
                        .body()// отримуємо тіло
                        .text(); // переводемо в текст

                // переводимо в список
                List<CurrencyRatePrivatResponseDto> responseDtos = convertResponceToList(response);
                return responseDtos.stream()
                        .map(dto -> new CurrencyRateDto(dto.getCcy(),dto.getBuy(),dto.getSale()))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private List<CurrencyRatePrivatResponseDto> convertResponceToList (String response) {
            Type type = TypeToken.getParameterized(List.class, CurrencyRatePrivatResponseDto.class).getType();
            Gson gson = new Gson();
            return gson.fromJson(response,type);

        }

    }


