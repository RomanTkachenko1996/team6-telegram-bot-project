package edu.goit.button;

import lombok.Data;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Button implements CreateButton {
    public static Button button = new Button();

    @Override
    public List<InlineKeyboardButton> createButton(String stringOnButton) {
        return Collections.singletonList(InlineKeyboardButton.builder().text(stringOnButton)
                .callbackData(stringOnButton)
                .build());
    }

    @SneakyThrows
    public SendMessage createMainButton(SendMessage message, Long id) {

        message.setText("greeting");
        message.setChatId(id);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(createButton("Отримати інформацію \uD83D\uDCB8"));
        rowList.add(createButton("Налаштування \uD83D\uDEE0"));

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder().keyboard(rowList).build();

        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    @SneakyThrows
    public SendMessage createSettingsButton(SendMessage message, long id) {

        message.setText("Налаштування бота: ");
        message.setChatId(id);

        List<List<InlineKeyboardButton>> tool = new ArrayList<>();
        tool.add(createButton("Кількість знаків після коми"));
        tool.add(createButton("Вибір банку"));
        tool.add(createButton("Вибір валюти"));
        tool.add(createButton("Час оповіщень"));
        tool.add(createButton("\uD83D\uDD19 Назад"));

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder().keyboard(tool).build();

        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    @SneakyThrows
    public SendMessage createSettingDelimiterButton(SendMessage massage, long id) {

        massage.setText("Скількі знаків ви хочете бачити після коми?");
        massage.setChatId(id);

        List<List<InlineKeyboardButton>> commasList = commas();
        commasList.add(createButton("\uD83D\uDD19 Назад"));

        InlineKeyboardMarkup keyboardMarkupForCommas = InlineKeyboardMarkup.builder().keyboard(commasList).build();

        massage.setReplyMarkup(keyboardMarkupForCommas);
        return massage;
    }

    private static List<List<InlineKeyboardButton>> commas() {
        return Stream.iterate(2, com -> com + 1)
                .limit(3)
                .map(com -> Collections.singletonList(InlineKeyboardButton.builder().text(String.valueOf(com))
                        .callbackData(String.valueOf(com))
                        .build()))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public SendMessage createSettingsBankButton(SendMessage massage, long id) { // Пока не буду делать метод под Enum

        massage.setText("Які банки хочете обрати?");
        massage.setChatId(id);

        List<List<InlineKeyboardButton>> bank = new ArrayList<>();
        bank.add(createButton("monobank"));
        bank.add(createButton("ПриватБанк"));
        bank.add(createButton("НБУ"));
        bank.add(createButton("\uD83D\uDD19 Назад"));

        InlineKeyboardMarkup keyboardMarkupForBank = InlineKeyboardMarkup.builder().keyboard(bank).build();

        massage.setReplyMarkup(keyboardMarkupForBank);
        return massage;
    }

    @SneakyThrows
    public <T extends Enum<T>> SendMessage createSettingsMoneyButton(SendMessage massage, long id, Enum<T> EUR, Enum<T> USD) {

        massage.setText("Яку валюту хочете обрати?");
        massage.setChatId(id);

        List<List<InlineKeyboardButton>> currency = new ArrayList<>();
        currency.add(createButton(EUR.name() + "\uD83D\uDCB6"));
        currency.add(createButton(USD.name() + "\uD83D\uDCB5"));
        currency.add(createButton("\uD83D\uDD19 Назад"));

        InlineKeyboardMarkup keyboardMarkupForCCY = InlineKeyboardMarkup.builder().keyboard(currency).build();

        massage.setReplyMarkup(keyboardMarkupForCCY);
        return massage;
    }

    @SneakyThrows
    public SendMessage createSettingTimeButton(SendMessage massage, long id) {

        massage.setText("Виберіть час, через який можу вам знову скинути курс валют");
        massage.setChatId(id);

        List<List<InlineKeyboardButton>> time = new ArrayList<>();
        time.add(appointedTime(9));
        time.add(appointedTime(12));
        time.add(appointedTime(15));
        time.add(createButton("Вимкнути повідомлення"));
        time.add(createButton("\uD83D\uDD19 Назад"));

        InlineKeyboardMarkup keyboardMarkupForTime = InlineKeyboardMarkup.builder().keyboard(time).build();

        massage.setReplyMarkup(keyboardMarkupForTime);
        return massage;
    }

    private static List<InlineKeyboardButton> appointedTime(Integer time) {
        return Stream.iterate(time, com -> com + 1)
                .limit(3)
                .map(com -> InlineKeyboardButton.builder().text(com + ":00").callbackData(com + ":00").build())
                .collect(Collectors.toList());
    }
}
//    @SneakyThrows
//    public SendMessage createSettingTimeButton(SendMessage massage, long id) {
//
//        massage.setText("Виберіть час, через який можу вам знову скинути курс валют");
//        massage.setChatId(id);
//
//        KeyboardRow turnMessage = new KeyboardRow();
//        turnMessage.add("Вимкнути повідомлення");
//
//        List<KeyboardRow> time = new ArrayList<>();
//        time.add(appointedTime(new KeyboardRow(),9));
//        time.add(appointedTime(new KeyboardRow(),12));
//        time.add(appointedTime(new KeyboardRow(),15));
//        time.add(turnMessage);
//
//        ReplyKeyboardMarkup keyboardMarkupForTime = ReplyKeyboardMarkup.builder().keyboard(time).build();
//
//        massage.setReplyMarkup(keyboardMarkupForTime);
//        return massage;
//    }
//
//    private static KeyboardRow appointedTime(KeyboardRow button, Integer time) {
//        List<String> times = Stream.iterate(time, com -> com + 1)
//                .limit(3)
//                .map(com -> com + ":00")
//                .collect(Collectors.toList());
//        button.addAll(times);
//        return button;
//    }