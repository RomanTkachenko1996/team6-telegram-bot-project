package edu.goit.feature.service.bot_service;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CallBackHandler {
    public static EditMessageText handleTimeUpdatesSelection(Update update) {
        String callBackData = update.getCallbackQuery().getData();
        String setText = "Виберіть час для отримання оновлень:";
        //creating a new list to update the keyboard
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        //creating the keyboard to return to the user
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllTimeUpdatesButtonsMarkup(rows);
        updateSelectedButtonsForTimeUpdates(update, callBackData, keyboard, rows);
        return getEditMessageResponse(update, keyboard, setText);
    }
    private static void updateSelectedButtonsForTimeUpdates(Update update, String callBackData,
                                                            InlineKeyboardMarkup keyboard,
                                                            List<List<InlineKeyboardButton>> rows) {
        switch (callBackData) {
            case "9:00" -> updateTimeButtonSelection("9:00",update,0,3,0,0,rows);
            case "10:00" -> updateTimeButtonSelection("10:00",update,0,3,1,0,rows);
            case "11:00" -> updateTimeButtonSelection("11:00",update,0,3,2,0,rows);
            case "12:00" -> updateTimeButtonSelection("12:00",update,3,6,0,1,rows);
            case "13:00" -> updateTimeButtonSelection("13:00",update,3,6,1,1,rows);
            case "14:00" -> updateTimeButtonSelection("14:00",update,3,6,2,1,rows);
            case "15:00" -> updateTimeButtonSelection("15:00",update,6,9,0,2,rows);
            case "16:00" -> updateTimeButtonSelection("16:00",update,6,9,1,2,rows);
            case "17:00" -> updateTimeButtonSelection("17:00",update,6,9,2,2,rows);
            case "Вимкнути повідомлення" ->
                    updateTimeButtonSelection("Вимкнути повідомлення",update,9,10,0,3,rows);
        }
        rows.add(ButtonsLists.getBackBtn());
        keyboard.setKeyboard(rows);
    }
    private static void updateTimeButtonSelection(String callback, Update update, int startSublist, int endSublist, int listItem, int rowsItem, List<List<InlineKeyboardButton>> rows){
        String setTest = callback + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
        List<InlineKeyboardButton> buttons0To2 = ButtonsLists.getAllTimeUpdatesBtns().subList(startSublist, endSublist);
        buttons0To2.get(listItem).setText(setTest);
        buttons0To2.get(listItem).setCallbackData(callback);
        rows.set(rowsItem,buttons0To2);
    }
    // --------- </вибір часу для оновлень>

    // --------- <вибір банку>
    public static EditMessageText handleComaSelection(Update update) {
        String callBackData = update.getCallbackQuery().getData();
        String setText = "Виберіть банк:";
        //creating a new list to update the keyboard
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        //creating the keyboard to return to the user
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllBanksButtonsMarkup(rows);
        updateSelectedButtonsForBanks(update, callBackData, keyboard, rows);
        return getEditMessageResponse(update, keyboard,setText);
    }
    public static EditMessageText handleBankSelection(Update update) {
        String callBackData = update.getCallbackQuery().getData();
        String setText = "Кількість знаків після коми:";
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        //creating the keyboard to return to the user
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllBanksButtonsMarkup(rows);
        updateSelectedСoma(update, callBackData, keyboard, rows);
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
    private static void updateSelectedСoma(Update update, String callBackData, InlineKeyboardMarkup keyboard, List<List<InlineKeyboardButton>> rows) {
        String buttonText;
        switch (callBackData) {
            case "2" : {
                buttonText = "2" + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rows.set(0, List.of(InlineKeyboardButton.builder().text(buttonText).callbackData("2").build()));
                break;
            }
            case "3":{
                buttonText = "3" + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rows.set(1, List.of(InlineKeyboardButton.builder().text(buttonText).callbackData("3").build()));
                break;
            }
            case "4" : {
                buttonText = "4" + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rows.set(2, List.of(InlineKeyboardButton.builder().text(buttonText).callbackData("4").build()));
                break;
            }
        }
        rows.add(ButtonsLists.getBackBtn());
        keyboard.setKeyboard(rows);
    }











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
