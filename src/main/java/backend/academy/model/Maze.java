package backend.academy.model;

/// Класс Maze для хранения информации о лабиринте и ее обработки.
///
/// @param height Высота лабиринта (количество вертикальных ячеек).
/// @param width  Ширина лабиринта (количество горизонтальных ячеек).
/// @param grid   Двумерный массив клеток лабиринта, хранящий сетку блоков лабиринта.
public record Maze(int height, int width, Cell[][] grid) {
}
