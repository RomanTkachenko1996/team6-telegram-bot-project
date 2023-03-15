package edu.goit.feature.service.bot_service;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CallBackHandler {
    // --------- <вибір банку>
    public static EditMessageText handleBankSelection(Update update) {
        String callBackData = update.getCallbackQuery().getData();
        String setText = "Виберіть банк:";
        //creating a new list to update the keyboard
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        //creating the keyboard to return to the user
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllBanksButtonsMarkup(rows);
        updateSelectedButtonsForBanks(update, callBackData, keyboard, rows);
        return getEditMessageResponse(update, keyboard,setText);
    }
    private static void updateSelectedButtonsForBanks(Update update, String callBackData, InlineKeyboardMarkup keyboard, List<List<InlineKeyboardButton>> rows) {
        String buttonText;
        switch (callBackData) {
            case "Монобанк" -> {
                buttonText = "Монобанк" + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rows.set(0, List.of(InlineKeyboardButton.builder().text(buttonText).callbackData("Монобанк").build()));
            }
            case "ПриватБанк" -> {
                buttonText = "ПриватБанк" + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rows.set(1, List.of(InlineKeyboardButton.builder().text(buttonText).callbackData("ПриватБанк").build()));
            }
            case "НБУ" -> {
                buttonText = "НБУ" + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rows.set(2, List.of(InlineKeyboardButton.builder().text(buttonText).callbackData("НБУ").build()));
            }
        }
        rows.add(ButtonsLists.getBackBtn());
        keyboard.setKeyboard(rows);
    }
    // --------- </вибір банку>

    /**
     * Common method for all EditMessage responses
     */
    private static EditMessageText getEditMessageResponse(Update update, InlineKeyboardMarkup keyboard, String setText) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessageText.setText(setText);
        editMessageText.setReplyMarkup(keyboard);
        return editMessageText;
    }
}
