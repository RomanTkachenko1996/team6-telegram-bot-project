package edu.goit.feature.service.bot_service;

import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static edu.goit.feature.service.bot_service.ButtonsLists.getAllSettingsBtns;
import static edu.goit.feature.service.bot_service.ButtonsLists.getAllTimeUpdatesBtns;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    public CurrencyTelegramBot() {
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
            String cb = update.getCallbackQuery().getData();
            final long ID = update.getCallbackQuery().getMessage().getChatId();
            SendMessage massage = new SendMessage();

            switch (cb) {
                case "Отримати інформацію \uD83D\uDCB8":
                    massage.setText("Not yet");
                    execute(massage);
                    break;
                case "Налаштування \uD83D\uDEE0":
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllSettingsButtonsMarkup());
                    execute(massage);
                    break;
                case "Кількість знаків після коми":
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllDigitsAfterCommaMarkup());
                    execute(massage);
                    break;
                case "Вибір банку":
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllBanksButtonsMarkup());
                    execute(massage);
                    break;
                case "Вибір валюти":
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllCurrenciesButtonsMarkup());
                    execute(massage);
                    break;
                case "Час оповіщень":
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllTimeUpdatesButtonsMarkup());
                    execute(massage);
                    break;
            }
        } else
            System.out.println("command not exist!");
    }
}