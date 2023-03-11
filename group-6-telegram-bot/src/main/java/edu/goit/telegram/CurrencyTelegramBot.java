package edu.goit.telegram;

import edu.goit.ccy.CCY;
import edu.goit.ccy.CurrencyPrivate;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

import static edu.goit.button.Button.button;


public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {

    public static final String NAME = "WeWatchTheCurrencies_Bot";
    public static final String TOKEN = "6185013864:AAFam0RvGuVJ7YV0U7CnBLuwmInhv7zC1aw";

    CurrencyTelegramBot() {
        register(new StartCommand());
    }

    @Override
    public String getBotUsername() {
        return NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    @SneakyThrows
    public void processNonCommandUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            String cb = update.getCallbackQuery().getData();
            final long ID = update.getCallbackQuery().getMessage().getChatId();
            SendMessage massage = new SendMessage();

            switch (cb) {
                case "Отримати інформацію \uD83D\uDCB8":
                    massage.setText(Objects.requireNonNull(CurrencyPrivate.getCurrency()));
                    massage.setChatId(ID);
                    execute(massage);
                    break;
                case "Збільшити можливості \uD83D\uDEE0":
                    execute(button.createSettingsButton(massage, ID));
                    break;
                case "Кількість знаків після коми":
                    execute(button.createSettingDelimiterButton(massage, ID));
                    break;
                case "Вибір банку":
                    execute(button.createSettingsBankButton(massage, ID));
                    break;
                case "Вибір валюти":
                    execute(button.createSettingsMoneyButton(massage, ID, CCY.EUR, CCY.USD));
                    break;
                case "Час оповіщень":
                    execute(button.createSettingTimeButton(massage, ID));
                    break;
            }
        }
        System.out.println("command not exist!");
    }
}