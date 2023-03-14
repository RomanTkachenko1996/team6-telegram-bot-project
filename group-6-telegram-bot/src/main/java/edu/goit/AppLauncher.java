package edu.goit;

import edu.goit.feature.service.bot_service.CurrencyTelegramBot;
import edu.goit.feature.service.bot_service.TelegramBotService;

import java.io.File;
import java.io.IOException;

public class AppLauncher {
    public static void main(String[] args) {
        File file = new File("BotCredentials.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//       new CurrencyTelegramBot();
       new TelegramBotService();
    }
}