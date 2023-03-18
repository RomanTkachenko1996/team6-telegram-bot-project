package edu.goit.feature.service.bot_service;

import edu.goit.feature.enums.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


public class CallBackHandler {

    // --------- <вибір часу для оновлень>
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
    private static void updateSelection(String callback,Update update,int startSublist, int endSublist,int listItem, int rowsItem, List<List<InlineKeyboardButton>> rows){
        String setTest = callback + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
        List<InlineKeyboardButton> buttons0To2 = ButtonsLists.getAllTimeUpdatesBtns().subList(startSublist, endSublist);
        buttons0To2.get(listItem).setText(setTest);
        buttons0To2.get(listItem).setCallbackData(callback);
        rows.set(rowsItem,buttons0To2);
    }
    private static void updateSelectedButtonsForTimeUpdates(Update update, String callBackData,
                                                            InlineKeyboardMarkup keyboard,
                                                            List<List<InlineKeyboardButton>> rows) {
        switch (callBackData) {
            case "9:00":
                updateSelection("9:00", update, 0, 3, 0, 0, rows);
                break;
            case "10:00":
                updateSelection("10:00", update, 0, 3, 1, 0, rows);
                break;
            case "11:00":
                updateSelection("11:00", update, 0, 3, 2, 0, rows);
                break;
            case "12:00":
                updateSelection("12:00", update, 3, 6, 0, 1, rows);
                break;
            case "13:00":
                updateSelection("13:00", update, 3, 6, 1, 1, rows);
                break;
            case "14:00":
                updateSelection("14:00", update, 3, 6, 2, 1, rows);
                break;
            case "15:00":
                updateSelection("15:00", update, 6, 9, 0, 2, rows);
                break;
            case "16:00":
                updateSelection("16:00", update, 6, 9, 1, 2, rows);
                break;
            case "17:00":
                updateSelection("17:00", update, 6, 9, 2, 2, rows);
                break;
            case "Вимкнути повідомлення":
                updateSelection("Вимкнути повідомлення", update, 9, 10, 0, 3, rows);
                break;
        }
        rows.add(ButtonsLists.getBackBtn());
        keyboard.setKeyboard(rows);
    }


    // --------- </вибір часу для оновлень>

    // --------- <вибір к-сті знаквів після коми>
    public static EditMessageText handleDigitsAfterCommaSelection(Update update) {
        String setText = "Виберіть кількість знаків після коми:";
        String callBackData = update.getCallbackQuery().getData();
        String callBackText = update.getCallbackQuery().getMessage().getText();
        //creating the keyboard to return to the user
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllDigitsAfterCommaMarkup();
        //creating the list of all buttons we have, to store edited marked ("selected") buttons
        List<InlineKeyboardButton> rowOfButtons = new ArrayList<>(ButtonsLists.getAllDigitsAfterCommaBtns());
        //creating a new list to update the keyboard
        List<List<InlineKeyboardButton>> rows = new ArrayList<>(List.of(rowOfButtons));
        updateSelectedButtonsForDigits(callBackData, callBackText, keyboard, rowOfButtons, rows);
        return getEditMessageResponse(update, keyboard,setText);
    }

    private static void updateSelectedButtonsForDigits(String callBackData, String callBackText, InlineKeyboardMarkup keyboard, List<InlineKeyboardButton> rowOfButtons, List<List<InlineKeyboardButton>> rows) {
        String buttonText;
        switch (callBackData) {
            case "2":
                buttonText = "2 " + (callBackText.contains("\u2705") ? "" : "\u2705");
                rowOfButtons.set(0, InlineKeyboardButton.builder().text(buttonText).callbackData("2").build());
                break;
            case "3":
                buttonText = "3 " + (callBackText.contains("\u2705") ? "" : "\u2705");
                rowOfButtons.set(1, InlineKeyboardButton.builder().text(buttonText).callbackData("3").build());
                break;
            case "4":
                buttonText = "4 " + (callBackText.contains("\u2705") ? "" : "\u2705");
                rowOfButtons.set(2, InlineKeyboardButton.builder().text(buttonText).callbackData("4").build());
                break;
        }
        rows.set(0, rowOfButtons);
        rows.add(ButtonsLists.getBackBtn()); // we do not forget about back brn
        keyboard.setKeyboard(rows);
    }
    // --------- </вибір к-сті знаквів після коми>

    // --------- <вибір валюти>

    public static EditMessageText handleCurrencySelection(Update update) {
        String callBackData = update.getCallbackQuery().getData();
        String setText = "Виберіть валюту:";
        //creating the keyboard to return to the user
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllCurrenciesButtonsMarkup();
        //creating the list of all buttons we have, to store edited marked ("selected") buttons
        List<InlineKeyboardButton> rowOfButtons = new ArrayList<>(ButtonsLists.getAllCurrenciesBtns());
        //creating a new list to update the keyboard
        List<List<InlineKeyboardButton>> rows = new ArrayList<>(List.of(rowOfButtons));
        updateSelectedButtonsForCurrency(update, callBackData, keyboard, rowOfButtons, rows);
        return getEditMessageResponse(update, keyboard,setText);
    }

    private static void updateSelectedButtonsForCurrency(Update update, String callBackData, InlineKeyboardMarkup keyboard, List<InlineKeyboardButton> rowOfButtons, List<List<InlineKeyboardButton>> rows) {
        String euro = Currency.EUR.name()+ " " + "\uD83C\uDDEA\uD83C\uDDFA";
        String dollar = Currency.USD.name()+ " " + "\uD83C\uDDFA\uD83C\uDDF8";
        String buttonText;
        switch (callBackData) {
            case "USD" -> {
                buttonText = dollar + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rowOfButtons.set(0, InlineKeyboardButton.builder().text(buttonText).callbackData(Currency.USD.name()).build());
            }
            case "EUR" -> {
                buttonText = euro + (update.getCallbackQuery().getMessage().getText().contains(" \u2705") ? "" : " \u2705");
                rowOfButtons.set(1, InlineKeyboardButton.builder().text(buttonText).callbackData(Currency.EUR.name()).build());
            }
        }
        rows.set(0, rowOfButtons);
        rows.add(ButtonsLists.getBackBtn());
        keyboard.setKeyboard(rows);
    }
    // --------- </вибір валюти>

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
    private static EditMessageText getEditMessageResponse(Update update, InlineKeyboardMarkup keyboard, String setText) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessageText.setText(setText);
        editMessageText.setReplyMarkup(keyboard);
        return editMessageText;
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
    public static SendMessage handleStartCommand(Update update) {
        InlineKeyboardMarkup keyboard = ButtonMarkups.createStartCommandMarkup();
        String setText = "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют";
        return sendMessage(update, keyboard, setText);
    }
    public static SendMessage handleChooseBankBtn(Update update) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllBanksButtonsMarkup(rows);
        String setText = "Виберіть банк:";
        return sendMessage(update, keyboard, setText);
    }

    public static SendMessage handleChooseTimeUpdatesBtn(Update update) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllTimeUpdatesButtonsMarkup(rows);
        String setText = "Виберіть час для отримання оновлень:";
        return sendMessage(update, keyboard, setText);
    }

    public static SendMessage handleChooseDigitsAfterCommaBtn(Update update) {
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllDigitsAfterCommaMarkup();
        String setText = "Виберіть кількість знаків після коми:";
        return sendMessage(update, keyboard, setText);
    }

    public static SendMessage handleChooseCurrencyBtn(Update update) {
        String setText = "Виберіть валюту:";
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllCurrenciesButtonsMarkup();
        return sendMessage(update, keyboard, setText);
    }


    private static SendMessage sendMessage(Update update, InlineKeyboardMarkup keyboard, String setText) {
        SendMessage message = new SendMessage();
        message.setText(setText);
        message.setChatId(Long.toString(update.getCallbackQuery().getMessage().getChatId()));
        message.setReplyMarkup(keyboard);
        return message;
    }

    public static SendMessage handleChooseSettingsBtn(Update update) {
        String setText = "Виберіть налаштування:";
        InlineKeyboardMarkup keyboard = ButtonMarkups.createAllSettingsButtonsMarkup();
        return sendMessage(update, keyboard, setText);
    }
    public static SendMessage handleShowInfoBtn(Update update, SettingsStorageDto settingsStorage) {
        String message = PrettyOutputForShowInfo.makeOutput(settingsStorage);
        System.out.println(message); // to trace the output in console
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(Long.toString(update.getCallbackQuery().getMessage().getChatId()));
        return sendMessage;
    }

}
