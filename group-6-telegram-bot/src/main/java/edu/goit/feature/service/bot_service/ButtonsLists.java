package edu.goit.feature.service.bot_service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static edu.goit.feature.Constants.*;
import static edu.goit.feature.enums.Currency.*;

public class ButtonsLists {

    public static List<InlineKeyboardButton> getShowInfoBtn() {
        return Stream.of(SHOW_INFO_BTN)
                .map(it ->
                        InlineKeyboardButton.builder()
                                .text(it)
                                .callbackData(it)
                                .build()
                ).collect(Collectors.toList());
    }

    public static List<InlineKeyboardButton> getSettingsBtn() {
        return Stream.of(SETTINGS_BTN)
                .map(it ->
                        InlineKeyboardButton.builder()
                                .text(it)
                                .callbackData(it)
                                .build()
                ).collect(Collectors.toList());
    }


    public static List<InlineKeyboardButton> getBackBtn() {
        return Stream.of(BACK_BTN)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build()
                ).collect(Collectors.toList());
    }

    public static List<InlineKeyboardButton> getAllSettingsBtns() {
        return Stream.of(CHOOSE_BANK_BTN,
                        CHOOSE_CCY_BTN,
                        DIGITS_CCY_BTN,
                        TIME_UPDATES_BTN)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .collect(Collectors.toList());
    }

    public static List<InlineKeyboardButton> getAllBanksBtns() {
        return Stream.of(MONO_NAME_BTN,
                        PRIVAT_NAME_BTN,
                        NBU_NAME_BTN)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .collect(Collectors.toList());
    }

    public static List<InlineKeyboardButton> getAllCurrenciesBtns() {
        return Stream.of(USD, EUR)
                .map(Enum::name)
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .peek(it -> {
                            if (it.getText().equals(EUR.name())) {
                                it.setText(it.getText() + " " + "\uD83D\uDCB6");
                            } else {
                                it.setText(it.getText() + " " + "\uD83D\uDCB5");
                            }
                        }
                )
                .collect(Collectors.toList());
    }

    public static List<InlineKeyboardButton> getAllDigitsAfterCommaBtns() {
        return Stream.of("2", "3", "4")
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .collect(Collectors.toList());
    }

    public static List<InlineKeyboardButton> getAllTimeUpdatesBtns() {
        return Stream.of("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00")
                .map(it -> InlineKeyboardButton.builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .collect(Collectors.toList());
    }

    public static String getTimeInformation(String timeString) {
        return Stream.builder()
                .add("Відпраляю вам повідомлення о ")
                .add(timeString)
                .build()
                .toString();
    }
}