package backend.academy.model.solver;

import backend.academy.model.Cell;
import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/// Класс для нахождения пути в заданном лабиринте с помощью алгоритма поиска в ширину.
public class BFSSolver extends Solver {

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {

        // Для каждой координаты храним предка для восстановления пути.
        int[] prev = new int[maze.height() * maze.width()];
        Arrays.fill(prev, -1);

        int startId = start.row() * maze.width() + start.col();
        int endId = end.row() * maze.width() + end.col();
        prev[startId] = startId;

        // Массив посещенных клеток.
        // Массив для хранения уже посещенных клеток.
        boolean[][] visited = new boolean[maze.height()][maze.width()];
        visited[start.row()][start.col()] = true;

        // Список направлений для проверки достижимых полей.
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int xCoordId = 0;
        int yCoordId = 1;

        List<Coordinate> cells = new ArrayList<>();
        cells.add(start);

        // Цикл алгоритма.
        while (!cells.isEmpty()) {
            Coordinate cell = cells.removeFirst();

            // Берем соседние клетки для текущей и обновляем их предков, если они еще не посещены.
            for (int[] direction : directions) {
                int nextX = cell.col() + direction[xCoordId];
                int nextY = cell.row() + direction[yCoordId];
                if (isInBounds(nextX, nextY, maze.height(), maze.width()) && !visited[nextY][nextX]
                    && maze.grid()[nextY][nextX].type() == Cell.Type.PASSAGE) {
                    prev[nextY * maze.width() + nextX] = cell.row() * maze.width() + cell.col();
                    cells.add(new Coordinate(nextY, nextX));
                    visited[nextY][nextX] = true;
                }
            }
            if (visited[end.row()][end.col()]) {
                break;
            }
        }

        return getPath(maze, prev, endId);
    }

    /// Метод для проверки нахождения координат внутри лабиринта.
    private boolean isInBounds(int x, int y, int height, int width) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
