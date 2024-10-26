package backend.academy.model.renderer;

import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.List;

/// Интерфейс, который описывает визуализацию заданного лабиринта (с путем/без пути) в консоли.
/// Реализующие его классы должны работать со строковым представлением лабиринта.
public interface Renderer {

    /// Метод для получения строкового представления лабиринта.
    String render(Maze maze);

    /// Метод для получения строкового представления лабиринта с найденным путем.
    String render(Maze maze, List<Coordinate> path);
}
