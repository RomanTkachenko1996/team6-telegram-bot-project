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
        return BotCredentialsReader.botUsernameReader();
    }

    @Override
    public String getBotToken() {
        return BotCredentialsReader.botTokenReader();
    }


    @Override
    public void processNonCommandUpdate(Update update) {
    }
}
