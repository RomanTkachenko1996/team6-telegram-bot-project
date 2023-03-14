package edu.goit.feature.service.bot_service;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class StartCommand extends BotCommand {

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        if (arguments == null || arguments.length == 0) {
            System.out.println("Start command!");
            InlineKeyboardMarkup keyboard = ButtonMarkups.createStartCommandMarkup();
            SendMessage sentStartingMessage = new SendMessage();
            sentStartingMessage.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
            sentStartingMessage.setChatId(chat.getId()); //Long.toString(chat.getId()) можно не писати
            sentStartingMessage.setReplyMarkup(keyboard);

            File videoFile = new File("D:\\gif\\ezgif-4-84c5c3a100.mp4");
            InputFile inputFile = new InputFile(videoFile);
            SendVideo video = new SendVideo();
            video.setChatId(chat.getId());
            video.setVideo(inputFile);

            try {
                absSender.execute(video);
                absSender.execute(sentStartingMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}