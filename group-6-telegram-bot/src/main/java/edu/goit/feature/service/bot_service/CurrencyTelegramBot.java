package edu.goit.feature.service.bot_service;

import edu.goit.feature.handler.TimeHandler;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {

    private final TimeHandler time;

    public CurrencyTelegramBot() {
        time = new TimeHandler();
        register(new StartCommand("start", "CurrencyTelegramBot started"));
    }

    @Override
    public String getBotUsername() {
        System.out.println(BotCredentialsReader.botUsernameReader());
        return BotCredentialsReader.botUsernameReader();
    }

    @Override
    public String getBotToken() {
        System.out.println(BotCredentialsReader.botTokenReader());
        return BotCredentialsReader.botTokenReader();
    }

    @SneakyThrows
    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            final long ID = update.getCallbackQuery().getMessage().getChatId();
            SendMessage massage = new SendMessage();

            switch (callbackData) {
                case "Показати курс \uD83D\uDCB8":
                    massage.setText("Not yet");
                    massage.setChatId(ID);
                    execute(massage);
                    break;
                case "Налаштування \uD83D\uDEE0":
                    massage.setText("Налаштування: ");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllSettingsButtonsMarkup());
                    execute(massage);
                    break;
                case "К-сть знаків після коми":
                    massage.setText("Скількі знаків після коми ви хочите бачити?");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllDigitsAfterCommaMarkup());
                    execute(massage);
                    break;
                case "Вибрати банк":
                    massage.setText("Який банк ви хочете обрати?");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllBanksButtonsMarkup());
                    execute(massage);
                    break;
                case "Вибрати валюту":
                    massage.setText("Яку валюту ви хочете обрати?");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllCurrenciesButtonsMarkup());
                    execute(massage);
                    break;
                case "Час для повідомлень від боту":
                    massage.setText("Заплануйте час для отримання повідомлення:");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllTimeUpdatesButtonsMarkup());
                    execute(massage);
                    break;
                case "9:00":
                    execute(time.sendSelectedTime(ID, "9:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "10:00":
                    execute(time.sendSelectedTime(ID, "10:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "11:00":
                    execute(time.sendSelectedTime(ID, "11:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "12:00":
                    execute(time.sendSelectedTime(ID, "12:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "13:00":
                    execute(time.sendSelectedTime(ID, "13:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "14:00":
                    execute(time.sendSelectedTime(ID, "14:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "15:00":
                    execute(time.sendSelectedTime(ID, "15:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "16:00":
                    execute(time.sendSelectedTime(ID, "16:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "17:00":
                    execute(time.sendSelectedTime(ID, "17:00"));
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
                case "DELETE":
                    execute(time.updateSelectedButtonsForTime(update, ID, callbackData));
                    break;
            }
        }
    }
}