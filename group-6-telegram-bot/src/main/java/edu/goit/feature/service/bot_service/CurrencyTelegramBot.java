package edu.goit.feature.service.bot_service;

import edu.goit.feature.service.bot_service.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    public CurrencyTelegramBot() {
        register(new StartCommand("start", "CurrencyTelegramBot started"));
    }

    @Override
    public String getBotUsername() {
        //TODO insert parser for Username
        return null;
    }

    @Override
    public String getBotToken() {
        //TODO insert parser for token
        return null;
    }


    @Override
    public void processNonCommandUpdate(Update update) {
    }
}
