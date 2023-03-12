package edu.goit.feature.service.bot_service;

import java.io.File;
import java.util.Scanner;

public class BotCredentialsReader {
        private static final File file = new File("resources/BotCredentials.txt");
        private static Scanner scanner;

        private static void reader() {
            try {
                scanner = new Scanner(file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static String botUsernameReader() {
            reader();
            return scanner.nextLine();
        }

        public static String botTokenReader() {
            reader();
            scanner.nextLine();
            return scanner.nextLine();
        }

    }
