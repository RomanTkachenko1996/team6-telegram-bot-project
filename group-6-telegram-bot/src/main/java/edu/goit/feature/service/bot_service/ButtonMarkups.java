package edu.goit.feature.service.bot_service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.goit.feature.service.bot_service.ButtonsLists.*;

public class ButtonMarkups {
    public static InlineKeyboardMarkup createAllCurrenciesButtonsMarkup() {
        List<InlineKeyboardButton> listOfButtons = getAllCurrenciesBtns();
        return InlineKeyboardMarkup.builder()
                .keyboard(Collections.singleton(listOfButtons))
                .keyboardRow(getBackBtn())
                .build();
    }

    public static InlineKeyboardMarkup createAllBanksButtonsMarkup() {
        List<InlineKeyboardButton> getAllBanks = getAllBanksBtns();
        List<List<InlineKeyboardButton>> banksRows = new ArrayList<>();
        for (InlineKeyboardButton bankButton : getAllBanks) {
            banksRows.add(Collections.singletonList(bankButton));
        }
        return InlineKeyboardMarkup.builder()
                .keyboard(banksRows)
                .keyboardRow(getBackBtn())
                .build();
    }

    public static InlineKeyboardMarkup createAllTimeUpdatesButtonsMarkup() {
        List<InlineKeyboardButton> buttons10 =  Collections.singletonList(InlineKeyboardButton.builder()
                .text("�������� �����������")
                .callbackData("DELETE")
                .build());

        List<List<InlineKeyboardButton>> banksRows = new ArrayList<>();
        banksRows.add(getAllTimeUpdatesBtns().subList(0, 3));
        banksRows.add(getAllTimeUpdatesBtns().subList(3, 6));
        banksRows.add(getAllTimeUpdatesBtns().subList(6, 9));
        banksRows.add(buttons10);

        return InlineKeyboardMarkup.builder()
                .keyboard(banksRows)
                .keyboardRow(getBackBtn())
                .build();
    }

    public static InlineKeyboardMarkup createAllDigitsAfterCommaMarkup() {
        return InlineKeyboardMarkup.builder()
                .keyboard(Collections.singleton(getAllDigitsAfterCommaBtns()))
                .keyboardRow(getBackBtn())
                .build();
    }

    public static InlineKeyboardMarkup createAllSettingsButtonsMarkup() {
        List<List<InlineKeyboardButton>> settingsRows = new ArrayList<>(); // ������ �������� ����� ������ ���� ����� ��� ������ ��� �� ������ ���� � CurrencyTelegramBot
        for (InlineKeyboardButton bankButton : getAllSettingsBtns()) {
            settingsRows.add(Collections.singletonList(bankButton));
        }
        return InlineKeyboardMarkup.builder()
                .keyboard(settingsRows)
                .keyboardRow(getBackBtn())
                .build();
    }
    
    public static InlineKeyboardMarkup createStartCommandMarkup() {
        return InlineKeyboardMarkup.builder()
                .keyboard(Collections.singleton(getShowInfoBtn()))
                .keyboard(Collections.singleton(getSettingsBtn()))
                .build();
    }
}