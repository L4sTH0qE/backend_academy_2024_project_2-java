package backend.academy.model;

import lombok.Getter;

/// Класс-запись для хранения полной информации о блоке лабиринта для его построения.
public record Cell(int row, int col, Type type) {
    @Getter  public enum Type {
        WALL('#'),
        PASSAGE(' ');

        // Символ клетки для отображения в консоли.
        // Сразу указываем здесь, чтобы было проще добавлять новые типы клеток лабиринта.
        private final char symbol;

        // Конструктор для инициализации символа типа клетки лабиринта.
        Type(char symbol) {
            this.symbol = symbol;
        }
    }
}
