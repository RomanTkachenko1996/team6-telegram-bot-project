package edu.goit.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotService {
    private CurrencyTelegramBot cb;
    public BotService(){
        cb = new CurrencyTelegramBot();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(cb);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
