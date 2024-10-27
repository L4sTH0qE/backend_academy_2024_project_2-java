package backend.academy.tests;

import backend.academy.model.Cell;
import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import backend.academy.model.solver.BFSSolver;
import backend.academy.model.solver.DFSSolver;
import backend.academy.model.solver.Solver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка работоспособности алгоритмов нахождения пути для лабиринта, в котором нет пути.
public class SolveInvalidMazePresetTest {
    static int height = 5;
    static int width = 5;
    static Cell[][] grid = new Cell[height][width];
    static Maze maze;

    int startXCoord = 0;
    int startYCoord = 0;
    int endXCoord = 4;
    int endYCoord = 4;
    Coordinate start = new Coordinate(startYCoord, startXCoord);
    Coordinate end = new Coordinate(endYCoord, endXCoord);

    Solver bfsSolver = new BFSSolver();
    Solver dfsSolver = new DFSSolver();

    @BeforeAll static void initialiseMap() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i % 2 == 0 && j % 2 == 0) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }

        maze = new Maze(height, width, grid);
    }

    @Test
    void testBFSSolver() {
        List<Coordinate> path = bfsSolver.solve(maze, start, end);
        assertThat(path).isNull();
    }

    @Test
    void testDFSSolver() {
        List<Coordinate> path = dfsSolver.solve(maze, start, end);
        assertThat(path).isNull();
    }
}
