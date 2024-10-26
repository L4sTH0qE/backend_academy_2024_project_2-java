package backend.academy.model.renderer;

import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.Arrays;
import java.util.List;

/// Класс для обычного отображения лабиринта в консоль
public class DefaultRenderer implements Renderer {

    /// Метод для возвращения лабиринта без пути в строковом представлении.
    public String render(Maze maze) {
        char wallSymbol = '⬛';
        char endSymbol = '\n';
        String vertSpaceSymbol = String.format("%4s", ' ');

        StringBuilder mazeStr = new StringBuilder();
        char[] wallLine = new char[maze.width() + 2];
        Arrays.fill(wallLine, wallSymbol);

        mazeStr.append(vertSpaceSymbol);
        // Добавляем горизонтальную шкалу для x-координаты.
        for (int i = 0; i < ((maze.width() + 1) / 2); ++i) {
            if (i % 2 == 0) {
                mazeStr.append(String.format("%4s", i + 1));
            } else {
                mazeStr.append(String.format("%5s", i + 1));
            }
        }
        mazeStr.append(endSymbol).append(vertSpaceSymbol).append(wallLine).append(endSymbol);

        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                if (j == 0) {
                    // Добавляем вертикальную шкалу для y-координаты.
                    if (i % 2 == 0) {
                        mazeStr.append(String.format("%4s", (i / 2) + 1));
                    } else {
                        mazeStr.append(vertSpaceSymbol);
                    }
                    mazeStr.append(wallSymbol);
                }
                mazeStr.append(maze.grid()[i][j].type().symbol());
            }
            mazeStr.append(wallSymbol).append(endSymbol);
        }
        mazeStr.append(vertSpaceSymbol).append(wallLine).append(endSymbol);
        return mazeStr.toString();
    }

    /// Метод для возвращения лабиринта с путем в строковом представлении.
    public String render(Maze maze, List<Coordinate> path) {
        // Если путь пустой - вызываем метод с одним параметром.
        if (path == null) {
            return render(maze);
        }

        char wallSymbol = '⬛';
        char pathSymbol = '❌';
        char endSymbol = '\n';
        String vertSpaceSymbol = String.format("%4s", ' ');

        StringBuilder mazeStr = new StringBuilder();
        char[] wallLine = new char[maze.width() + 2];
        Arrays.fill(wallLine, wallSymbol);

        mazeStr.append(vertSpaceSymbol);
        // Добавляем горизонтальную шкалу для x-координаты.
        for (int i = 0; i < ((maze.width() + 1) / 2); ++i) {
            if (i % 2 == 0) {
                mazeStr.append(String.format("%4s", i + 1));
            } else {
                mazeStr.append(String.format("%5s", i + 1));
            }
        }
        mazeStr.append(endSymbol).append(vertSpaceSymbol).append(wallLine).append(endSymbol);

        // Создаем массив булевых значений и ставим true для клеток, принадлежащих пути.
        boolean[][] isInPath = new boolean[maze.height()][maze.width()];
        for (Coordinate pathCell: path) {
            isInPath[pathCell.row()][pathCell.col()] = true;
        }

        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                if (j == 0) {
                    // Добавляем вертикальную шкалу для y-координаты.
                    if (i % 2 == 0) {
                        mazeStr.append(String.format("%4s", (i / 2) + 1));
                    } else {
                        mazeStr.append(vertSpaceSymbol);
                    }
                    mazeStr.append(wallSymbol);
                }
                if (isInPath[i][j]) {
                    mazeStr.append(pathSymbol);
                } else {
                    mazeStr.append(maze.grid()[i][j].type().symbol());
                }
            }
            mazeStr.append(wallSymbol).append(endSymbol);
        }
        mazeStr.append(vertSpaceSymbol).append(wallLine).append(endSymbol);
        return mazeStr.toString();
    }
}
