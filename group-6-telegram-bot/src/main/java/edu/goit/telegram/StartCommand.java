package edu.goit.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartCommand extends BotCommand {
    public StartCommand() {
        super("start", "start command"); //Обробка на команду /start
        System.out.println("Start command executed!");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String greeting = "Привіт! Я допоможу вам відстежити курс валют: ";

        SendMessage message = new SendMessage();
        message.setText(greeting);
        message.setChatId(chat.getId());

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(createButton("Отримати інформацію \uD83D\uDCB8"));
        rowList.add(createButton("Збільшити можливості \uD83D\uDEE0"));

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder().keyboard(rowList).build();

        message.setReplyMarkup(keyboardMarkup);

        SendVideo video = new SendVideo();
        video.setChatId(chat.getId());

        File videoFile = new File("D:\\gif\\ezgif-4-84c5c3a100.mp4");
        InputFile inputFile = new InputFile(videoFile);
        video.setVideo(inputFile);


        try {
            absSender.execute(video);
            absSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<InlineKeyboardButton> createButton(String stringOnButton){
        return Collections.singletonList(InlineKeyboardButton.builder().text(stringOnButton)
                .callbackData(stringOnButton) // что прийдет при нажатии на кнопку
                .build());
    }
}