package edu.goit.feature.handler;

import edu.goit.feature.service.bot_service.BotCredentialsReader;
import edu.goit.feature.service.bot_service.ButtonMarkups;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public class TimeToSendMessages extends TelegramLongPollingCommandBot {

    private String selectedTime;
    private long chatId;

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
    public void processNonCommandUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            final long ID = update.getCallbackQuery().getMessage().getChatId();
            System.out.println("button push");
            String subCallbackData = update.getCallbackQuery().getData();
            SendMessage massage = new SendMessage();

            switch (subCallbackData) {
                case "��� ��� ���������� �� ����":
                    massage.setText("���������� ��� ��� ��������� �����������:");
                    massage.setChatId(ID);
                    massage.setReplyMarkup(ButtonMarkups.createAllTimeUpdatesButtonsMarkup());
                    execute(massage);

                    this.chatId = ID;
                    break;
                case "9:00":
                    selectedTime = "9:00";
                    sendSelectedTime(ID, selectedTime);
                    break;
                case "DELETE":
                    selectedTime = null;
                    massage.setText("�� �������� �����������");
                    massage.setChatId(this.chatId);
                    execute(massage);
                    break;
            }
        } else {
            System.out.println("command not exist!");
        }
    }

    @SneakyThrows
    private void sendSelectedTime(long chatId, String selectedTime) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("�� ������� �����: " + selectedTime);
        execute(message);
    }
}


//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            SendMessage message = new SendMessage();
//                            message.setChatId(chatId);
//                            message.setText(messageText);
//                            try {
//                                execute(message);
//                            } catch (TelegramApiException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, getDelayUntilNextTime(this.selectedTime));
//                    massage.setText("����������� ����������� �� " + this.selectedTime);
//                    massage.setChatId(this.chatId);
//                    execute(massage);