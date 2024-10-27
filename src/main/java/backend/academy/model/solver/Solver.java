package backend.academy.model.solver;

import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// Интерфейс, который описывает нахождение пути в заданном лабиринте из стартовой точки в конечную.
/// Реализующие его классы должны предоставлять алгоритм поиска пути.
public abstract class Solver {

    /// Метод для нахождения пути в лабиринте между двумя заданными клетками.
    abstract public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    protected List<Coordinate> getPath(Maze maze, int[] prev, int endId) {
        int endIndex = endId;
        // Возвращаем null, если конечная клетка не достижима из стартовой клетки.
        if (prev[endIndex] == -1) {
            return null;
        }

        // Список клеток, составляющих искомый путь.
        List<Coordinate> path = new ArrayList<>();

        // Собираем путь по предкам.
        path.add(new Coordinate(endIndex / maze.width(), endIndex % maze.width()));
        int coordsCounter = maze.height() * maze.width() - 1;
        while (endIndex != prev[endIndex] && coordsCounter > 0) {
            path.add(new Coordinate(prev[endIndex] / maze.width(), prev[endIndex] % maze.width()));
            endIndex = prev[endIndex];
            --coordsCounter;
        }

        // Попали в какой-то цикл (невозможно, но пусть проверка будет).
        if (endIndex != prev[endIndex]) {
            return null;
        }

        // Переворачиваем список.
        Collections.reverse(path);
        return path;
    }
}
