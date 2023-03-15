package edu.goit.feature.service.bot_service;

import edu.goit.feature.handler.TimeToSendMessages;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    private final TimeToSendMessages timeToSendMessages;

    public CurrencyTelegramBot() {
        timeToSendMessages = new TimeToSendMessages();
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
                case "�������� ���� \uD83D\uDCB8":
                    massage.setText("Not yet");
                    massage.setChatId(ID);
                    execute(massage);
                    break;
                case "������������ \uD83D\uDEE0":
                    massage.setText("������������: ");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllSettingsButtonsMarkup());
                    execute(massage);
                    break;
                case "�-��� ����� ���� ����":
                    massage.setText("����� ����� ���� ���� �� ������ ������?");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllDigitsAfterCommaMarkup());
                    execute(massage);
                    break;
                case "������� ����":
                    massage.setText("���� ���� �� ������ ������?");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllBanksButtonsMarkup());
                    execute(massage);
                    break;
                case "������� ������":
                    massage.setText("��� ������ �� ������ ������?");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllCurrenciesButtonsMarkup());
                    execute(massage);
                    break;
                case "��� ��� ���������� �� ����":
                    timeToSendMessages.processNonCommandUpdate(update);
                    break;
            }
        }
    }
}