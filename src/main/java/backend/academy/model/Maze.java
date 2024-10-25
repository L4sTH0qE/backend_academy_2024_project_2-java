package backend.academy.model;

import lombok.Getter;

/// Класс Maze для хранения информации о лабиринте и ее обработки.
@Getter public final class Maze {

    // Высота лабиринта (количество вертикальных ячеек).
    private final int height;

    // Ширина лабиринта (количество горизонтальных ячеек).
    private final int width;

    // Двумерный массив клеток лабиринта, хранящий сетку блоков лабиринта.
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }
}
