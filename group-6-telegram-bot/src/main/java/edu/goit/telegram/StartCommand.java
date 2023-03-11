package edu.goit.telegram;

import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.File;

import static edu.goit.button.Button.button;


public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "start command");
        System.out.println("Start command executed!");
    }

    @Override
    @SneakyThrows
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        SendMessage message = new SendMessage();

        SendVideo video = new SendVideo();
        video.setChatId(chat.getId());

        File videoFile = new File("D:\\gif\\ezgif-4-84c5c3a100.mp4");
        InputFile inputFile = new InputFile(videoFile);
        video.setVideo(inputFile);

        absSender.execute(video);
        absSender.execute(button.createMainButton(message, chat.getId()));
    }
}