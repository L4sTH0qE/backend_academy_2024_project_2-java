package backend.academy.model.generator;

import backend.academy.controller.AppController;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.List;

/// Класс для генерации лабиринта с помощью алгоритма Краскала.
public class KruskalGenerator implements Generator {


    public Maze generate(int height, int width) {

        // Массив для хранения сетки лабиринта.
        Cell[][] grid = new Cell[height][width];

        // Список стен-ребер (стены, расположенные не по диагонали).
        List<Wall> walls = new ArrayList<>();

        // Количество клеток в лабиринте
        int size = 0;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                // Заполняем сетку (сразу отмечаем клетки).
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                    ++size;
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
                // Обновляем список стен-ребер, проверяя направление стены.
                if (i % 2 == 1 && j % 2 == 0) {
                    walls.add(new Wall(j, i, j, i - 1, j, i + 1));
                } else if (i % 2 == 0 && j % 2 == 1) {
                    walls.add(new Wall(j, i, j - 1, i, j + 1, i));
                }
            }
        }

        // Храним непересекающиеся множества клеток лабиринта.
        DisjointSet cellSet = new DisjointSet(size);

        // Цикл алгоритма (проходимся по списку стен-ребер и пытаемся объединять множества клеток).
        while (!walls.isEmpty()) {
            // Выбираем случайную стену
            int wallId = AppController.SECURE_RANDOM.nextInt(walls.size());
            Wall wall = walls.get(wallId);
            walls.remove(wallId);

            // Получаем индексы разделенных стеной клеток по их координатам.
            int firstCellId = (wall.fromY / 2) * ((width + 1) / 2) + wall.fromX / 2;
            int secondCellId = (wall.toY / 2) * ((width + 1) / 2) + wall.toX / 2;

            // Пытаемся объединить множества.
            boolean isUnited = cellSet.union(firstCellId, secondCellId);
            // Если объединили - "прорубаем" стену.
            if (isUnited) {
                grid[wall.wallY][wall.wallX] = new Cell(wall.wallY, wall.wallX, Cell.Type.PASSAGE);
            }
        }

        return new Maze(height, width, grid);
    }

    /// Вспомогательный класс для описания соседних стен (ребер) вместе с достижимыми из них клетками.
    private static class DisjointSet {

        // Массив с представителями множества для каждой вершины графа.
        int[] array;

        DisjointSet(int size) {
            array = new int[size];
            for (int i = 0; i < size; ++i) {
                array[i] = i;
            }
        }

        /// Метод для нахождения и обновления представителя множества для данного элемента.
        public int find(int el) {
            if (array[el] != el) {
                array[el] = find(array[el]);
            }
            return array[el];
        }

        /// Метод для объединения двух множеств по заданным представителям
        /// (если элементы из разных множеств, то добавляем множество второго элемента в множество первого элемента).
        public boolean union(int first, int second) {
            int rootFirst = find(first);
            int rootSecond = find(second);
            if (rootFirst != rootSecond) {
                array[rootSecond] = rootFirst;
                return true;
            }
            return false;
        }
    }

    /// Вспомогательный класс для описания соседних стен (ребер) вместе с достижимыми из них клетками.
    private static class Wall {
        // Координаты стены.
        int wallX, wallY;

        // Координаты исходной клетки.
        int fromX, fromY;

        // Координаты достижимой клетки.
        int toX, toY;

        Wall(int wallX, int wallY, int fromX, int fromY, int toX, int toY) {
            this.wallX = wallX;
            this.wallY = wallY;
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }
    }
}
