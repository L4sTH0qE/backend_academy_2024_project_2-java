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
        char[] chars = new char[maze.width() + 1];
        Arrays.fill(chars, '#');
        mazeStr.append(chars);

        for (int i = 0; i < maze.height(); ++i) {
            for (int j = 0; j < maze.width(); ++j) {
                if (j == 0) {
                    mazeStr.append("#\n#");
                }
                mazeStr.append(maze.grid()[i][j].type().symbol());
            }
        }
        mazeStr.append("#\n#");
        mazeStr.append(chars);
        return mazeStr.toString();
    }

    /// Метод для возвращения лабиринта с путем в строковом представлении.
    public String render(Maze maze, List<Coordinate> path) {
        return "";
    }
}
