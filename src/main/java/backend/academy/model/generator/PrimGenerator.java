package backend.academy.model.generator;

import backend.academy.controller.AppController;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.List;

/// Класс для генерации лабиринта с помощью алгоритма Прима.
public class PrimGenerator implements Generator {

    // Массив для хранения уже посещенных клеток.
    private boolean[][] visited;

    public Maze generate(int height, int width) {
        // Массив для хранения сетки лабиринта.
        Cell[][] grid = new Cell[height][width];

        visited = new boolean[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }

        // Случайно выбираем координаты стартовой клетки алгоритма.
        int startX = AppController.SECURE_RANDOM.nextInt(0, (width + 1) / 2) * 2;
        int startY = AppController.SECURE_RANDOM.nextInt(0, (height + 1) / 2) * 2;
        visited[startY][startX] = true;
        grid[startY][startX] = new Cell(startY, startX, Cell.Type.PASSAGE);

        // Список доступных соседних клеток (неиспользованных ребер графа).
        List<Wall> neighbours = new ArrayList<>();

        // Добавляем соседние стены (ребра) для стартовой клетки.
        addNeighbours(startX, startY, height, width, neighbours);

        // Цикл алгоритма.
        while (!neighbours.isEmpty()) {
            // Выбираем случайную стену
            int neighbourId = AppController.SECURE_RANDOM.nextInt(neighbours.size());
            Wall neighbour = neighbours.get(neighbourId);
            neighbours.remove(neighbourId);

            // Если поле, достижимое через данную стену, не посещено - "прорубаем" путь к ней и обновляем список стен.
            if (!visited[neighbour.cellY][neighbour.cellX]) {
                grid[neighbour.wallY][neighbour.wallX] = new Cell(neighbour.wallY, neighbour.wallX, Cell.Type.PASSAGE);
                visited[neighbour.wallY][neighbour.wallX] = true;
                grid[neighbour.cellY][neighbour.cellX] = new Cell(neighbour.cellY, neighbour.cellX, Cell.Type.PASSAGE);
                visited[neighbour.cellY][neighbour.cellX] = true;
                addNeighbours(neighbour.cellX, neighbour.cellY, height, width, neighbours);
            }
        }
        return new Maze(height, width, grid);
    }

    /// Метод, который добавляет соседние стены (ребра) в список, если достижимые через них поля еще не были посещены.
    private void addNeighbours(int x, int y, int height, int width, List<Wall> neighbours) {
        // Список направлений для проверки достижимых полей.
        int[][] directions = {{0, -2}, {2, 0}, {0, 2}, {-2, 0}};
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (isInBounds(nextX, nextY, height, width) && !visited[nextY][nextX]) {
                neighbours.add(new Wall(x + direction[0] / 2, y + direction[1] / 2, nextX, nextY));
            }
        }
    }

    /// Метод для проверки нахождения координат внутри лабиринта.
    private boolean isInBounds(int x, int y, int height, int width) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /// Вспомогательный класс для описания соседних стен (ребер) вместе с достижимыми из них клетками.
    private static class Wall {
        // Координаты стены.
        int wallX, wallY;

        // Координаты достижимой клетки.
        int cellX, cellY;

        Wall(int wallX, int wallY, int cellX, int cellY) {
            this.wallX = wallX;
            this.wallY = wallY;
            this.cellX = cellX;
            this.cellY = cellY;
        }
    }
}
