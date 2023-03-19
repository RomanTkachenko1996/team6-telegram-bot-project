package edu.goit.feature.service.bot_service.buttons;

import edu.goit.feature.Constants;
import edu.goit.feature.enums.Currency;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Stream;

public class ButtonsLists {
    public static List<InlineKeyboardButton> getShowInfoBtn() {
        return Stream.of(Constants.SHOW_INFO_BTN)
                .map(it ->
                        InlineKeyboardButton.builder()
                                .text(it)
                                .callbackData(it)
                                .build()
                ).toList();
    }
    public static List<InlineKeyboardButton> getSettingsBtn() {
        return Stream.of(Constants.SETTINGS_BTN)
                .map(it ->
                        InlineKeyboardButton.builder()
                                .text(it)
                                .callbackData(it)
                                .build()
                ).toList();
    }


    public static List<InlineKeyboardButton> getBackBtn() {
        return Stream.of(Constants.BACK_BTN)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build()
                ).toList();
    }
    public static List <InlineKeyboardButton> getAllSettingsBtns(){
        return Stream.of(Constants.CHOOSE_BANK_BTN,
                        Constants.CHOOSE_CCY_BTN,
                        Constants.DIGITS_CCY_BTN,
                        Constants.TIME_UPDATES_BTN)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .toList();
    }
    public static List<InlineKeyboardButton> getAllBanksBtns() {
        return Stream.of(Constants.MONO_NAME_BTN,
                        Constants.PRIVAT_NAME_BTN,
                        Constants.NBU_NAME_BTN)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .toList();
    }

    public static List<InlineKeyboardButton> getAllCurrenciesBtns(){
        return Stream.of(Currency.USD, Currency.EUR)
                .map(Enum::name)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .peek(it -> {
                            if (it.getText().equals(Currency.EUR.name())) {
                                it.setText(it.getText() + " " + "\uD83D\uDCB6");
                            } else {
                                it.setText(it.getText() + " " + "\uD83D\uDCB5");
                            }
                        }
                )
                .toList();
    }
    public static List<InlineKeyboardButton> getAllDigitsAfterCommaBtns() {
        return Stream.of("2","3","4")
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .toList();
    }

    public static List<InlineKeyboardButton> getAllTimeUpdatesBtns() {
        return Stream.of("9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","Вимкнути повідомлення")
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .toList();
    }
}