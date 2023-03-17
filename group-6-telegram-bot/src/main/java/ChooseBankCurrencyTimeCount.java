import edu.goit.feature.currency_dto.CurrencyRateDto;
import edu.goit.feature.enums.BankName;
import edu.goit.feature.enums.Currency;
import edu.goit.feature.enums.Time;
import edu.goit.feature.service.bank_service.CurrencyService;
import edu.goit.feature.service.bank_service.MonobankCurrencyService;
import edu.goit.feature.service.bank_service.NBUCurrencyService;
import edu.goit.feature.service.bank_service.PrivatBankCurrencyService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChooseBankCurrencyTimeCount {
    private List<CurrencyService> retrievalServices = List.of(
            new MonobankCurrencyService(),
            new PrivatBankCurrencyService(),
            new NBUCurrencyService()
    );

    public List<CurrencyRateDto> getActualRates(){
        return retrievalServices.stream()
                .map(CurrencyService::getCurrencyRates)
                .flatMap(java.util.Collection::stream)
                .collect(Collectors.toList());
    }

    public Map<String, CurrencyRateDto> getNeedBank(BankName bankName) {
        List<CurrencyRateDto> rates = getActualRates();
        rates = rates.stream()
                .filter(item -> bankName.equals(item.getName()))
                .collect(Collectors.toList());
        return null;
    }


    public Map<String, CurrencyRateDto> getRates(Currency currency) {
        List<CurrencyRateDto> rates = getActualRates();
        rates = rates.stream()
                .filter(item -> currency.equals(item.getCurrency()))
                .collect(Collectors.toList());


//        List  <CurrencyRateDto> buyRate = rates.stream()
//                .filter(CurrencyRateDto -> getActualRates().equals(currency))
//                .toList();


        List  <CurrencyRateDto> buyRate = rates.stream()
                .filter(CurrencyRateDto -> {
                    return CurrencyRateDto.getBuyRate().equals("buy");
                }).collect(Collectors.toList());


        List  <CurrencyRateDto> sellRate = rates.stream()
                .filter(CurrencyRateDto -> CurrencyRateDto.getSellRate().equals("SELL"))
                .collect(Collectors.toList());




        return (Map<String, CurrencyRateDto>) List.of(sellRate, buyRate);

    }

    public static Time convertToEnum(String text) {
        for (Time time : Time.values()) {
            if (String.valueOf(time.getTime()).equals(text)) {
                return time;
            }
        }
        return null;
    }

}


