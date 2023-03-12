package edu.goit.telegram;
import edu.goit.feature.service.bot_service.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
        private CurrencyServise currencyServise;

        public CurrencyTelegramBot(){
                register(new StartCommand("start", "CurrencyTelegramBot started"));

        }

        @Override
        public String getBotUsername() {
            return BotConstants.BOT_NAME;
        }

        @Override
        public void processNonCommandUpdate(Update update) {
                if (update.hasMessage()) {
                        Message message = update.getMessage();
                        if (message.hasText()) {
                                SendMessage echoMessage = new SendMessage();
                                echoMessage.setChatId(message.getChatId());
                                echoMessage.setText("Hey here is your message:\n" + message.getText());

                                try {
                                        execute(echoMessage);
                                } catch (TelegramApiException e) {
                                        e.printStackTrace();
                                        throw new RuntimeException(e);
                                }
                        }
                }
        }
}
