package edu.goit.feature.service.bot_service;

import edu.goit.feature.Constants;
import edu.goit.feature.currency_dto.CurrencyRateDto;
import edu.goit.feature.enums.Currency;
import edu.goit.feature.service.bank_service.MonobankCurrencyService;
import edu.goit.feature.service.bank_service.NBUCurrencyService;
import edu.goit.feature.service.bank_service.PrivatBankCurrencyService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class PrettyOutputForShowInfo {
    public static String makeOutput(SettingsStorageDto settingsStorage){
        Currency currency = settingsStorage.getCurrency();
        String bank = settingsStorage.getBank();
        String digitsAfterDots = settingsStorage.getDigitsAfterDots();
        List<CurrencyRateDto> list;
        if (bank.equals(Constants.PRIVAT_NAME_BTN)){
          list = new PrivatBankCurrencyService().getCurrencyRates();
        } else if (bank.equals(Constants.MONO_NAME_BTN)){
            list = new MonobankCurrencyService().getCurrencyRates();
        } else{
            list = new NBUCurrencyService().getCurrencyRates();
        }
        BigDecimal buy = list.stream()
                .filter(it -> it.getCurrency().equals(currency))
                .map(CurrencyRateDto::getBuyRate)
                .findFirst()
                .orElseThrow().
                setScale(Integer.parseInt(digitsAfterDots), RoundingMode.HALF_EVEN);
        BigDecimal sale = list.stream()
                .filter(it -> it.getCurrency().equals(currency))
                .map(CurrencyRateDto::getSellRate)
                .findFirst()
                .orElseThrow()
                .setScale(Integer.parseInt(digitsAfterDots), RoundingMode.HALF_EVEN);
        String template = ("\uD83D\uDCC8 Курс ${bank}\n\n" +
                "\uD83D\uDCB0 Валюта \u27A1\uFE0F ${currency}\n\n" +
                "\uD83D\uDCCD Продаж ${sale}\n" +
                "\uD83D\uDCCD Купівля ${buy}")
                .replace("${bank}",bank)
                .replace("${currency}",String.valueOf(currency))
                .replace("${sale}", sale.toString());

        if (bank.equals(Constants.MONO_NAME_BTN) || bank.equals(Constants.PRIVAT_NAME_BTN)){
            return template.replace("${buy}",buy.toString());
        } else {
            return template.replace("\uD83D\uDCCD Купівля ${buy}","");
        }
    }
}
