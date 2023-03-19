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
import java.util.HashMap;
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
    SettingsStorageDto settingsStorageDto;


    public CurrencyTelegramBot() {
        register(new StartCommand("start", "CurrencyTelegramBot started"));

        settingsStorageDto = new SettingsStorageDto(Currency.USD, Constants.PRIVAT_NAME_BTN, "2", "9:00");
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

        Map<String, Consumer<String>> mapButtons = new HashMap<>();
        mapButtons.put(Constants.SHOW_INFO_BTN, (value) -> {
            SendMessage message = CallBackHandler.handleShowInfoBtn(update, settingsStorageDto);
            executeMessage(message);
        });
        mapButtons.put(Constants.SETTINGS_BTN, (value) -> {
            responseFprBackBtn = update.getCallbackQuery().getData();
            SendMessage message = CallBackHandler.handleChooseSettingsBtn(update);
            executeMessage(message);
        });
        mapButtons.put(Constants.CHOOSE_BANK_BTN, (value) -> {
            responseFprBackBtn = update.getCallbackQuery().getData();
            SendMessage message = CallBackHandler.handleChooseBankBtn(update);
            executeMessage(message);
        });
        mapButtons.put(Constants.CHOOSE_CCY_BTN, (value) -> {
            responseFprBackBtn = update.getCallbackQuery().getData();
            SendMessage message = CallBackHandler.handleChooseCurrencyBtn(update);
            executeMessage(message);
        });
        mapButtons.put(Constants.TIME_UPDATES_BTN, (value) -> {
            responseFprBackBtn = update.getCallbackQuery().getData();
            SendMessage message = CallBackHandler.handleChooseTimeUpdatesBtn(update);
            executeMessage(message);
        });
        mapButtons.put(Constants.DIGITS_CCY_BTN, (value) -> {
            responseFprBackBtn = update.getCallbackQuery().getData();
            executeMessage(CallBackHandler.handleChooseDigitsAfterCommaBtn(update));
        });
        mapButtons.put(Currency.EUR.name(), (value) -> {
            settingsStorageDto.setCurrency(Currency.valueOf(callBackQuery));
            executeEditMessageText(CallBackHandler.handleCurrencySelection(update));
        });
        mapButtons.put(Currency.USD.name(), (value) -> {
            settingsStorageDto.setCurrency(Currency.valueOf(callBackQuery));
            executeEditMessageText(CallBackHandler.handleCurrencySelection(update));
        });
        mapButtons.put(Constants.PRIVAT_NAME_BTN, (value)  -> {
            settingsStorageDto.setBank(callBackQuery);
            EditMessageText editMessageText = CallBackHandler.handleBankSelection(update);
            executeEditMessageText(editMessageText);
        });
        mapButtons.put(Constants.MONO_NAME_BTN, (value) -> {
            settingsStorageDto.setBank(callBackQuery);
            EditMessageText editMessageText = CallBackHandler.handleBankSelection(update);
            executeEditMessageText(editMessageText);
        });
        mapButtons.put(Constants.NBU_NAME_BTN, (value) -> {
            settingsStorageDto.setBank(callBackQuery);
            EditMessageText editMessageText = CallBackHandler.handleBankSelection(update);
            executeEditMessageText(editMessageText);
        });
        mapButtons.put("2", (value) -> {
            settingsStorageDto.setDigitsAfterDots(callBackQuery);
            EditMessageText editMessageText = CallBackHandler.handleDigitsAfterCommaSelection(update);
            executeEditMessageText(editMessageText);
        });
        mapButtons.put("3", (value) -> {
            settingsStorageDto.setDigitsAfterDots(callBackQuery);
            EditMessageText editMessageText = CallBackHandler.handleDigitsAfterCommaSelection(update);
            executeEditMessageText(editMessageText);
        });
        mapButtons.put("4", (value) -> {
            settingsStorageDto.setDigitsAfterDots(callBackQuery);
            EditMessageText editMessageText = CallBackHandler.handleDigitsAfterCommaSelection(update);
            executeEditMessageText(editMessageText);
        });
        mapButtons.put(Constants.BACK_BTN, (value) -> {
            SendMessage message;
            if (responseFprBackBtn.equals(Constants.SETTINGS_BTN) || responseFprBackBtn.equals(Constants.BACK_BTN)){
                message = CallBackHandler.handleStartCommand(update);
            } else {
                message = CallBackHandler.handleChooseSettingsBtn(update);
                responseFprBackBtn = update.getCallbackQuery().getData();
            }
            executeMessage(message);
        });

        Optional.ofNullable(mapButtons.get(callBackQuery)).orElse((value) -> {
            String chatID = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            EditMessageText editMessageText = CallBackHandler.handleTimeUpdatesSelection(update);
            executeEditMessageText(editMessageText);
            settingsStorageDto.setTimeForUpdates(callBackQuery);
            if (!(stateMachines.containsKey(chatID))) {
                StateMachine stateMachine = new StateMachine();
                stateMachines.put(chatID, stateMachine);
                stateMachine.addUser(new MessageUsers(chatID));
            }
            stateMachines.get(chatID).handle(callBackQuery);
        }).accept(callBackQuery);
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

            sendMessage.setText(PrettyOutputForShowInfo.makeOutput(settingsStorageDto));
            sendMessage.setChatId(chatId);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

