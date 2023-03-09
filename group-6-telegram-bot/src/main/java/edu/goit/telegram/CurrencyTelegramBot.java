package edu.goit.telegram;

import edu.goit.button.CurrencyPrivate;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {

    CurrencyTelegramBot() {
        register(new StartCommand());
    }

    public static final String NAME = "WeWatchTheCurrencies_Bot";
    public static final String TOKEN = "6185013864:AAFam0RvGuVJ7YV0U7CnBLuwmInhv7zC1aw";

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
        if (update.hasCallbackQuery()) { //нажали ли на кнопку
            String cb = update.getCallbackQuery().getData();
            long ID =  update.getCallbackQuery().getMessage().getChatId();
            SendMessage massage = new SendMessage();

            if (cb.equals("Отримати інформацію \uD83D\uDCB8")) {
                massage.setText(Objects.requireNonNull(CurrencyPrivate.getCurrency()));
                massage.setChatId(ID);
                execute(massage);
            } else if (cb.equals("Збільшити можливості \uD83D\uDEE0")) {
                massage.setChatId(ID);
                massage.setText("TOOLS");
                execute(massage);
            }
        }
        System.out.println("command not exist!");
    }
}
