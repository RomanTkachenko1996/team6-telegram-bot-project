package edu.goit.feature.service.bot_service;

import edu.goit.feature.service.bot_service.StartCommand;
import edu.goit.feature.service.statemachine.StateMachineUsers;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    private ScheduledExecutorService scheduledExecutorService;
    public CurrencyTelegramBot() {
        register(new StartCommand("start", "CurrencyTelegramBot started"));
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
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


    private class MessageUsers implements StateMachineUsers {

        private String chatId;

        public MessageUsers(String chatId) {
            this.chatId = chatId;
        }

        @Override
        public void onTimeReceived(int time) {
            LocalTime postingTime = LocalTime.of(time, 0);
            LocalTime currentTime = LocalTime.now();
            long delay = Duration.between(currentTime, postingTime).toSeconds();

            scheduledExecutorService.schedule(
                    this::sendText,
                    delay,
                    TimeUnit.SECONDS
            );
        }

        private void sendText() {
            SendMessage sendMessage = new SendMessage();

            //sendMessage.setText(text);
            sendMessage.setChatId(chatId);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
