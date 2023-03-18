package edu.goit.feature.service.bot_service;


import edu.goit.feature.Constants;
import edu.goit.feature.enums.Currency;

import edu.goit.feature.service.bot_service.StartCommand;
import edu.goit.feature.service.statemachine.StateMachine;
import edu.goit.feature.service.statemachine.StateMachineUsers;


import lombok.SneakyThrows;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {

    Map<String, StateMachine> stateMachines;
    static String responseFprBackBtn;
    private ScheduledExecutorService scheduledExecutorService;
    SettingsStorage settingsStorage;


    public CurrencyTelegramBot() {
        register(new StartCommand("start", "CurrencyTelegramBot started"));
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        stateMachines = new ConcurrentHashMap<>();
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

        String callBackQuery = update.getCallbackQuery().getData();
        Map<String, Consumer<String>> mapButtons = Map.of(
                Constants.SHOW_INFO_BTN.equals(callBackQuery), (value) -> {
                    SendMessage message = HandleShowInfo.handleCallBack(update, settingsStorage);
                    executeMessage(message);
                },
                Constants.SETTINGS_BTN.equals(callBackQuery), (value) -> {
                    responseFprBackBtn = update.getCallbackQuery().getData();
                    SendMessage message = HandleSettings.handleCallBack(update);
                    executeMessage(message);
                },
                Constants.CHOOSE_BANK_BTN.equals(callBackQuery), (value) -> {
                    responseFprBackBtn = update.getCallbackQuery().getData();
                    SendMessage message = HandleBanksBtn.handleCallBack(update);
                    executeMessage(message);
                },
                Constants.CHOOSE_CCY_BTN.equals(callBackQuery), (value) -> {
                    responseFprBackBtn = update.getCallbackQuery().getData();
                    SendMessage message = HandleCurrencyBtn.handleCallBack(update);
                    executeMessage(message);
                },
                Constants.TIME_UPDATES_BTN.equals(callBackQuery), (value) -> {
                    responseFprBackBtn = update.getCallbackQuery().getData();
                    SendMessage message = HandleTimeUpdateBtn.handleCallBack(update);
                    executeMessage(message);
                },
                Constants.DIGITS_CCY_BTN.equals(callBackQuery), (value) -> {
                    responseFprBackBtn = update.getCallbackQuery().getData();
                    executeMessage(HandleDigitsAfterDotBtn.handleCallBack(update));
                },
                callBackQuery.equals(Currency.EUR.name()) || callBackQuery.equals(Currency.USD.name()), (value) -> {
                    settingsStorage.setCurrency(Currency.valueOf(callBackQuery));
                    executeEditMessageText(CurrencySelectionHandler.handleCallBack(update));
                },
                Constants.PRIVAT_NAME_BTN.equals(callBackQuery)
                || Constants.MONO_NAME_BTN.equals(callBackQuery)
                || Constants.NBU_NAME_BTN.equals(callBackQuery), (value) -> {
                    settingsStorage.setBank(callBackQuery);
                    EditMessageText editMessageText = BankSelectionHandler.handleCallBack(update);
                    executeEditMessageText(editMessageText);
                },
                "2".equals(callBackQuery) || "3".equals(callBackQuery) || "4".equals(callBackQuery), (value) -> {
                    settingsStorage.setDigitsAfterDots(callBackQuery);
                    EditMessageText editMessageText = DigitsAfterDotSelectionHandler.handleCallBack(update);
                    executeEditMessageText(editMessageText);
                },
                Constants.BACK_BTN.equals(callBackQuery), (value) -> {
                    SendMessage message;
                    if (responseFprBackBtn.equals(Constants.SETTINGS_BTN) || responseFprBackBtn.equals(Constants.BACK_BTN)){
                        message = HandleStart.handleCallBack(update);
                    } else {
                        message = HandleSettings.handleCallBack(update);
                        responseFprBackBtn = update.getCallbackQuery().getData();
                    }
                    executeMessage(message);
                }
        );
        Optional.ofNullable(mapButtons.get(callBackQuery)).orElse((value) -> {
            String chatID = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            EditMessageText editMessageText = CallBackHandler.handleTimeUpdatesSelection(update);
            executeEditMessageText(editMessageText);
            settingsStorage.setTimeForUpdates(callBackQuery);
            if (!(stateMachines.containsKey(chatID))) {
                StateMachine stateMachine = new StateMachine();
                stateMachines.put(chatID, stateMachine);
                stateMachine.addUser(new MessageUsers(chatID));
            }
            stateMachines.get(chatID).handle(callBackQuery);
        });

    }

    private void executeMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void executeEditMessageText(EditMessageText editMessageText){
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
}

