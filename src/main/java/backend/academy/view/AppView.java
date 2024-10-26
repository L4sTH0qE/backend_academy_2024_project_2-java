package backend.academy.view;

import lombok.experimental.UtilityClass;
import java.util.Scanner;

/// Вспомогательный класс для выделения методов вывода в консоль, необходимых для использования в нескольких классах.
@UtilityClass
public class AppView {

    /// Метод для очистки окна консоли.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /// Метод для вывода сообщения об ошибке.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printInvalidCommand() {
        System.out.println("Invalid command.\n================");
    }

    /// Метод для вывода сообщения об ошибке при вводе не числа.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printInvalidValue() {
        System.out.println("Input value must be integer.\n============================");
    }

    /// Метод для вывода сообщения об ошибке при вводе числа меньше 1.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printInvalidInteger() {
        System.out.println("Integer value must be higher than 0.\n====================================");
    }

    /// Метод для вывода сообщения об ожидании ввода пользователя.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printInput() {
        System.out.print("\nYour input> ");
    }

    /// Метод для вывода сообщения при запуске приложения.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void printEntryMessage() {
        System.out.println("Welcome to a Maze creator!\n==========================");
    }

    /// Метод для вывода сообщения о завершении работы программы.
    @SuppressWarnings("RegexpSinglelineJava")
    public static void exit() {
        System.out.println("Exiting...");
    }

    /// Метод для вывода главного меню и получения выбора опции от пользователя.
    @SuppressWarnings("RegexpSinglelineJava")
    public static String getOptionFromUser(Scanner scanner) {
        System.out.println("Main menu");
        System.out.println("1 - Create maze");
        System.out.println("q - Exit");
        AppView.printInput();
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    /// Метод для вывода сообщения об ожидании ввода пользователем ширины лабиринта.
    @SuppressWarnings("RegexpSinglelineJava")
    public static String getWidthFromUser(Scanner scanner) {
        System.out.println("Enter maze width (> 0):");
        AppView.printInput();
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    /// Метод для вывода сообщения об ожидании ввода пользователем высоты (длины) лабиринта.
    @SuppressWarnings("RegexpSinglelineJava")
    public static String getHeightFromUser(Scanner scanner) {
        System.out.println("Enter maze height (> 0):");
        AppView.printInput();
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }
}
