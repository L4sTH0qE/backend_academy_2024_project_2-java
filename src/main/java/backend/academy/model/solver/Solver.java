package backend.academy.model.solver;

import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.List;

/// Интерфейс, который описывает нахождение пути в заданном лабиринте из стартовой точки в конечную.
/// Реализующие его классы должны предоставлять алгоритм поиска пути.
public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
