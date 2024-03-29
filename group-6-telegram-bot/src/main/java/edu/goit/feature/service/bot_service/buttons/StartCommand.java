package edu.goit.feature.service.bot_service.buttons;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class StartCommand extends BotCommand {

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        InlineKeyboardMarkup keyboard =  ButtonMarkups.createStartCommandMarkup();
        SendMessage sentStartingMessage = new SendMessage();
        sentStartingMessage.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
        sentStartingMessage.setChatId(Long.toString(chat.getId()));
        sentStartingMessage.setReplyMarkup(keyboard);
        try {
            absSender.execute(sentStartingMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}