package backend.academy.controller;

import backend.academy.view.AppView;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/// Основной класс для работы с меню приложения.
@Log4j2
@UtilityClass
public class AppController {

    // Для получения ввода пользователя используется объект типа Scanner из состава JDK(JRE) API
    // Для инициализации SCANNER'а используется объект стандартного потока ввода - System.in
    public static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    // Экземпляр для генерации хорошего случайного числа.
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /// Метод для отображения окна входа в программу.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void start() {
        try {
            AppView.clear();
            AppView.printEntryMessage();

            // Выводим меню и ждем выбора пользователя, пока не будет выбрана опция "q" (завершение работы)
            while (true) {
                String choice = AppView.getOptionFromUser(SCANNER);
                switch (choice) {
                    case "1":
                        initialiseCreator();
                        break;
                    case "q", "Q":
                        exit();
                        break;
                    default:
                        AppView.printInvalidCommand();
                }
            }
            // Если возникла ошибка во время работы программы.
        } catch (Exception ex) {
            log.error("An unexpected error occurred while the program was running!");
            log.error("Details: {}", ex.getMessage());
            exit();
        }
    }

    /// Метод для получения уровня сложности для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static int getWidth() {
        AppView.clear();
        while (true) {
            String choice = AppView.getWidthFromUser(SCANNER);
            int defaultWidth = 10;
            if (choice.isEmpty()) {
                return defaultWidth;
            } else {
                try {
                    return Integer.parseInt(choice);
                } catch (NumberFormatException e) {
                    AppView.printInvalidValue();
                }
            }
        }
    }

    /// Метод для получения категории слова для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    private static int getHeight() {
        AppView.clear();
        while (true) {
            String choice = AppView.getHeightFromUser(SCANNER);
            int defaultHeight = 10;
            if (choice.isEmpty()) {
                return defaultHeight;
            } else {
                try {
                    return Integer.parseInt(choice);
                } catch (NumberFormatException e) {
                    AppView.printInvalidValue();
                }
            }
        }
    }

    /// Метод для инициализации игровой сессии.
    private static void initialiseCreator() {
        int width = getWidth();
        int height = getHeight();
        AppView.clear();
    }

    /// Метод для завершения работы программы.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void exit() {
        AppView.exit();
        System.exit(0);
    }
}
