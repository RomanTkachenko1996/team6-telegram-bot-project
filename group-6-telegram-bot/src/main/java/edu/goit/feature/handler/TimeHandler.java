package edu.goit.feature.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static edu.goit.feature.service.bot_service.ButtonsLists.getAllTimeUpdatesBtns;
import static edu.goit.feature.service.bot_service.ButtonsLists.getBackBtn;


public class TimeHandler {

    public SendMessage sendSelectedTime(long chatId, String selectedTime) { // виводить повідомлення коли нажимаєшь на кнопку
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Повідомлення буде надіслане о " + selectedTime + " годині");
        return message;
    }

    public EditMessageReplyMarkup updateSelectedButtonsForTime(Update update, long ID, String callBackData) {

        List<List<InlineKeyboardButton>> time = putDataOnTheKeyboard();

        String buttonText;

        switch (callBackData) {
            case "9:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(0, 0, buttonText, "9:00", time);
                break;
            case "10:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(0, 1, buttonText, "10:00", time);
                break;
            case "11:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(0, 2, buttonText, "11:00", time);
                break;
            case "12:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(1, 0, buttonText, "12:00", time);
                break;
            case "13:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(1, 1, buttonText, "13:00", time);
                break;
            case "14:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(1, 2, buttonText, "14:00", time);
                break;
            case "15:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(2, 0, buttonText, "15:00", time);
                break;
            case "16:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(2, 1, buttonText, "16:00", time);
                break;
            case "17:00":
                buttonText = callBackData + ((update.getCallbackQuery().getMessage().getText().contains(" \u2705")) ? "" : " \u2705");
                changeButtonText(2, 2, buttonText, "17:00", time);
                break;
            case "DELETE":
                rollBackTheKeyboard(time);
                break;
        }

        return EditMessageReplyMarkup.builder()
                .chatId(ID)
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(time)
                        .keyboardRow(getBackBtn())
                        .build())
                .build();
    }

    private static List<List<InlineKeyboardButton>> putDataOnTheKeyboard() { // бере дані по клаві
        List<InlineKeyboardButton> buttons10 = Collections.singletonList(InlineKeyboardButton.builder()
                .text("Вимкнути повідомлення")
                .callbackData("DELETE")
                .build());

        List<List<InlineKeyboardButton>> banksRows = new ArrayList<>();
        banksRows.add(getAllTimeUpdatesBtns().subList(0, 3));
        banksRows.add(getAllTimeUpdatesBtns().subList(3, 6));
        banksRows.add(getAllTimeUpdatesBtns().subList(6, 9));
        banksRows.add(buttons10);

        return banksRows;
    }

    private static void changeButtonText(int row, int column, String newText,
                                         String callbackData, List<List<InlineKeyboardButton>> time) {
        time.get(row).set(column, InlineKeyboardButton.builder()
                .text(newText)
                .callbackData(callbackData)
                .build());
    } // цей метод звертається до кнопки і змінює її

    private static void rollBackTheKeyboard(List<List<InlineKeyboardButton>> time) { // метод чистить клаву від смайлів
        List<List<InlineKeyboardButton>> newTime = time.stream()
                .filter(button -> button.contains(" \u2705"))
                .map(button -> "")
                .map(button -> Collections.singletonList(InlineKeyboardButton.builder()
                        .text(button)
                        .callbackData(button)
                        .build()))
                .collect(Collectors.toList());

        InlineKeyboardMarkup.builder()
                .keyboard(newTime)
                .keyboardRow(getBackBtn())
                .build();
    }
}