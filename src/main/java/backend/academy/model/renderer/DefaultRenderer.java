package backend.academy.model.renderer;

import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.Arrays;
import java.util.List;

/// Класс для обычного отображения лабиринта в консоль
public class DefaultRenderer implements Renderer {

    private static final String FIRST_FORMATTER = "%4s";
    private static final String SECOND_FORMATTER = "%5s";

    private static final char WALL_SYMBOL = '⬛';
    private static final char END_SYMBOL = '\n';

    private static final String VERT_SPACE_SYMBOL = String.format(FIRST_FORMATTER, ' ');

    private StringBuilder mazeStr;

    private char[] wallLine;

    /// Метод для возвращения лабиринта без пути в строковом представлении.
    public String render(Maze maze) {

        initialRender(maze);

        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                if (j == 0) {
                    // Добавляем вертикальную шкалу для y-координаты.
                    if (i % 2 == 0) {
                        mazeStr.append(String.format(FIRST_FORMATTER, (i / 2) + 1));
                    } else {
                        mazeStr.append(VERT_SPACE_SYMBOL);
                    }
                    mazeStr.append(WALL_SYMBOL);
                }
                mazeStr.append(maze.grid()[i][j].type().symbol());
            }
            mazeStr.append(WALL_SYMBOL).append(END_SYMBOL);
        }
        mazeStr.append(VERT_SPACE_SYMBOL).append(wallLine).append(END_SYMBOL);
        return mazeStr.toString();
    }

    /// Метод для возвращения лабиринта с путем в строковом представлении.
    public String render(Maze maze, List<Coordinate> path) {
        // Если путь пустой - вызываем метод с одним параметром.
        if (path == null) {
            return render(maze);
        }
        initialRender(maze);
        String pathSymbol = "\uD83D\uDFE8";
        String startSymbol = "\uD83D\uDFE9";
        String finishSymbol = "\uD83D\uDFE5";

        // Создаем массив булевых значений и ставим true для клеток, принадлежащих пути.
        boolean[][] isInPath = new boolean[maze.height()][maze.width()];
        for (Coordinate pathCell: path) {
            isInPath[pathCell.row()][pathCell.col()] = true;
        }
        Coordinate start = path.getFirst();
        Coordinate end = path.getLast();

        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                if (j == 0) {
                    // Добавляем вертикальную шкалу для y-координаты.
                    if (i % 2 == 0) {
                        mazeStr.append(String.format(FIRST_FORMATTER, (i / 2) + 1));
                    } else {
                        mazeStr.append(VERT_SPACE_SYMBOL);
                    }
                    mazeStr.append(WALL_SYMBOL);
                }
                if (isInPath[i][j]) {
                    if (i == start.row() && j == start.col()) {
                        mazeStr.append(startSymbol);
                    } else if (i == end.row() && j == end.col()) {
                        mazeStr.append(finishSymbol);
                    } else {
                        mazeStr.append(pathSymbol);
                    }
                } else {
                    mazeStr.append(maze.grid()[i][j].type().symbol());
                }
            }
            mazeStr.append(WALL_SYMBOL).append(END_SYMBOL);
        }
        mazeStr.append(VERT_SPACE_SYMBOL).append(wallLine).append(END_SYMBOL);
        return mazeStr.toString();
    }

    private void initialRender(Maze maze) {
        mazeStr = new StringBuilder();
        wallLine = new char[maze.width() + 2];
        Arrays.fill(wallLine, WALL_SYMBOL);

        mazeStr.append(VERT_SPACE_SYMBOL);
        // Добавляем горизонтальную шкалу для x-координаты.
        for (int i = 0; i < ((maze.width() + 1) / 2); ++i) {
            if (i % 2 == 0) {
                mazeStr.append(String.format(FIRST_FORMATTER, i + 1));
            } else {
                mazeStr.append(String.format(SECOND_FORMATTER, i + 1));
            }
        }
        mazeStr.append(END_SYMBOL).append(VERT_SPACE_SYMBOL).append(wallLine).append(END_SYMBOL);
    }
}
