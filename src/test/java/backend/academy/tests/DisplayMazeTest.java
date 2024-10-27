package backend.academy.tests;

import backend.academy.model.Cell;
import backend.academy.model.Maze;
import backend.academy.model.renderer.DefaultRenderer;
import backend.academy.model.renderer.Renderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка корректного отображения лабиринтов в консоли.
public class DisplayMazeTest {
    static int height = 5;
    static int width = 5;
    static Cell[][] grid = new Cell[height][width];
    static Maze maze;

    Renderer renderer = new DefaultRenderer();

    String result = """
           1    2   3
        ⬛⬛⬛⬛⬛⬛⬛
       1⬛⬜⬜⬜⬜⬜⬛
        ⬛⬜⬛⬜⬛⬜⬛
       2⬛⬜⬜⬜⬜⬜⬛
        ⬛⬜⬛⬜⬛⬜⬛
       3⬛⬜⬜⬜⬜⬜⬛
        ⬛⬛⬛⬛⬛⬛⬛
    """;

    @BeforeAll static void initialiseMap() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i % 2 != 0 && j % 2 != 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                }
            }
        }

        maze = new Maze(height, width, grid);
    }

    @Test
    void testRenderer() {
        String mazeStr = renderer.render(maze);
        assertThat(mazeStr).isEqualTo(result);
    }
}
