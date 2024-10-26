package backend.academy.model.renderer;

import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import java.util.Arrays;
import java.util.List;

/// Класс для обычного отображения лабиринта в консоль
public class DefaultRenderer implements Renderer {

    /// Метод для возвращения лабиринта без пути в строковом представлении.
    public String render(Maze maze) {
        StringBuilder mazeStr = new StringBuilder();
        char[] wallLine = new char[maze.width() + 1];
        Arrays.fill(wallLine, '⬛');
        String wallEndLine = "⬛\n⬛";
        mazeStr.append(wallLine);

        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                if (j == 0) {
                    mazeStr.append(wallEndLine);
                }
                mazeStr.append(maze.grid()[i][j].type().symbol());
            }
        }
        mazeStr.append(wallEndLine);
        mazeStr.append(wallLine);
        return mazeStr.toString();
    }

    /// Метод для возвращения лабиринта с путем в строковом представлении.
    public String render(Maze maze, List<Coordinate> path) {
        return "Path";
    }
}
