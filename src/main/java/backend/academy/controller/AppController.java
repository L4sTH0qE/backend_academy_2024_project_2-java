package backend.academy.controller;

import backend.academy.model.CreatorModel;
import backend.academy.view.AppView;
import backend.academy.view.CreatorView;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

/// Основной класс для работы с меню приложения.
@Log4j2
@UtilityClass
public class AppController {

    // Для получения ввода пользователя используется объект типа Scanner из состава JDK(JRE) API
    // Для инициализации SCANNER'а используется объект стандартного потока ввода - System.in
    public static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    // Ограничения на размер лабиринта.
    private static final int MAX_WIDTH = 50;
    private static final int MAX_HEIGHT = 100;

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
    public static int getWidth() {
        AppView.clear();
        while (true) {
            String choice = AppView.getWidthFromUser(SCANNER);
            try {
                int width = Integer.parseInt(choice);
                if (width > 0 && width <= MAX_WIDTH) {
                    return width;
                }
                AppView.printInvalidInteger();
            } catch (NumberFormatException e) {
                AppView.printInvalidValue();
            }
        }
    }

    /// Метод для получения категории слова для новой игры.
    @SuppressWarnings("RegexpSinglelineJava")
    public static int getHeight() {
        AppView.clear();
        while (true) {
            String choice = AppView.getHeightFromUser(SCANNER);
            try {
                int height = Integer.parseInt(choice);
                if (height > 0 && height <= MAX_HEIGHT) {
                    return height;
                }
                AppView.printInvalidInteger();
            } catch (NumberFormatException e) {
                AppView.printInvalidValue();
            }
        }
    }

    /// Метод для инициализации игровой сессии.
    private static void initialiseCreator() {
        int width = getWidth();
        int height = getHeight();
        AppView.clear();
        CreatorModel creatorModel = new CreatorModel(height, width);
        CreatorView creatorView = new CreatorView();
        CreatorController creatorController = new CreatorController(creatorModel, creatorView);
        creatorController.start();
    }

    /// Метод для завершения работы программы.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void exit() {
        AppView.exit();
        System.exit(0);
    }
}
