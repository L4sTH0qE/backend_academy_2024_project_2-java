package backend.academy.model.solver;

import backend.academy.model.Cell;
import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/// Класс для нахождения пути в заданном лабиринте с помощью алгоритма поиска в глубину.
public class DFSSolver implements Solver {

    // Массив для хранения уже посещенных клеток.
    private boolean[][] visited;

    // Для каждой координаты храним предка для восстановления пути.
    private int[] prev;

    // Лабиринт, в котором нужно найти путь.
    private Maze maze;

    private boolean isEndVisited;

    // Список направлений для проверки достижимых полей.
    private final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {

        isEndVisited = false;

        prev = new int[maze.height() * maze.width()];
        Arrays.fill(prev, -1);

        int startId = start.row() * maze.width() + start.col();
        int endId = end.row() * maze.width() + end.col();
        prev[startId] = startId;

        // Массив посещенных клеток.
        visited = new boolean[maze.height()][maze.width()];

        this.maze = maze;

        dfs(start, endId);

        // Возвращаем null, если конечная клетка не достижима из стартовой клетки.
        if (prev[endId] == -1) {
            return null;
        }

        // Список клеток, составляющих искомый путь.
        List<Coordinate> path = new ArrayList<>();

        // Собираем путь по предкам.
        path.add(new Coordinate(endId / maze.width(), endId % maze.width()));
        int coordsCounter = maze.height() * maze.width() - 1;
        while (endId != prev[endId] && coordsCounter > 0) {
            path.add(new Coordinate(prev[endId] / maze.width(), prev[endId] % maze.width()));
            endId = prev[endId];
            --coordsCounter;
        }

        // Попали в какой-то цикл (невозможно, но пусть проверка будет).
        if (endId != prev[endId]) {
            return null;
        }

        // Переворачиваем список.
        Collections.reverse(path);
        return path;
    }

    // Основной метод для реализации рекурсивной версии алгоритма поиска в глубину.
    private void dfs(Coordinate cur, int endId) {
        int xCoordId = 0;
        int yCoordId = 1;
        visited[cur.row()][cur.col()] = true;
        // Выполняем проверку, что текущая вершина является конечной для поиска.
        if (cur.row() * maze.width() + cur.col() == endId) {
            isEndVisited = true;
        }
        // Берем соседние клетки для текущей и обновляем их предков, если они еще не посещены.
        for (int[] direction : directions) {
            if (isEndVisited) {
                break;
            }
            int nextX = cur.col() + direction[xCoordId];
            int nextY = cur.row() + direction[yCoordId];
            if (isInBounds(nextX, nextY, maze.height(), maze.width()) && !visited[nextY][nextX]
                && maze.grid()[nextY][nextX].type() == Cell.Type.PASSAGE) {
                prev[nextY * maze.width() + nextX] = cur.row() * maze.width() + cur.col();
                dfs(new Coordinate(nextY, nextX), endId);
            }
        }
    }

    /// Метод для проверки нахождения координат внутри лабиринта.
    private boolean isInBounds(int x, int y, int height, int width) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
