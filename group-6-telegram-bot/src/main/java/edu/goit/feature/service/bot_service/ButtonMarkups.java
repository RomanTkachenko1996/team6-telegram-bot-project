package edu.goit.feature.service.bot_service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static InlineKeyboardMarkup createAllBanksButtonsMarkup(List<List<InlineKeyboardButton>> banksRows) {
        List<InlineKeyboardButton> getAllBanks = getAllBanksBtns();
        for (InlineKeyboardButton bankButton : getAllBanks) {
            banksRows.add(Collections.singletonList(bankButton));
        }
        return InlineKeyboardMarkup.builder()
                .keyboard(banksRows)
                .keyboardRow(getBackBtn())
                .build();
    }

    public static InlineKeyboardMarkup createAllTimeUpdatesButtonsMarkup(List<List<InlineKeyboardButton>> banksRows) {
        List<InlineKeyboardButton> buttons0To2 =  getAllTimeUpdatesBtns().subList(0, 3);
        List<InlineKeyboardButton> buttons3To5 = getAllTimeUpdatesBtns().subList(3, 6);
        List<InlineKeyboardButton> buttons6To8 = getAllTimeUpdatesBtns().subList(6, 9);
        List<InlineKeyboardButton> buttons9 = Collections.singletonList(getAllTimeUpdatesBtns().get(9));
        banksRows.addAll(List.of(buttons0To2,buttons3To5,buttons6To8,buttons9));
        return InlineKeyboardMarkup.builder()
                .keyboard(Arrays.asList(
                        buttons0To2,
                        buttons3To5,
                        buttons6To8,
                        buttons9
                ))
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
        List<List<InlineKeyboardButton>> settingsRows = new ArrayList<>();
        for (InlineKeyboardButton bankButton : getAllSettingsBtns()) {
            settingsRows.add(Collections.singletonList(bankButton));
        }
        return InlineKeyboardMarkup.builder()
                .keyboard(settingsRows)
                .keyboardRow(getBackBtn())
                .build();
    }

    public static InlineKeyboardMarkup createAllDigitsAfterCommaButtonsMarkup() {
        return InlineKeyboardMarkup.builder()
                .keyboard(Collections.singleton(getAllDigitsAfterCommaBtns()))
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
