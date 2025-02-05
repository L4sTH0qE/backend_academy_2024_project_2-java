package backend.academy.model;

import backend.academy.model.generator.Generator;
import backend.academy.model.solver.Solver;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/// Класс для описания логики создателя лабиринтов и текущего состояния лабиринта.
@Getter public class CreatorModel {

    // Поле, хранящее информацию о количестве клеток в высоте лабиринта.
    private final int height;

    // Поле, хранящее информацию о количестве клеток в ширине лабиринта.
    private final int width;

    // Поле, хранящее алгоритм для генерации лабиринта.
    @Setter private Generator generator;

    // Поле, хранящее алгоритм для нахождения пути в лабиринте.
    @Setter private Solver solver;

    // Поле, хранящее сгенерированный лабиринт.
    @Setter private Maze maze;

    // Поле, хранящее путь в лабиринте.
    @Setter private List<Coordinate> path;

    public CreatorModel(int height, int width) {
        this.height = 2 * height - 1;
        this.width = 2 * width - 1;
    }

    /// Метод для получения лабиринта из вызова метода генератора лабиринтов.
    public void generate() {
        maze = generator.generate(height, width);
    }

    /// Метод для получения лабиринта из вызова метода генератора лабиринтов.
    public void solve(Coordinate start, Coordinate end) {
        path = solver.solve(maze, start, end);
    }
}
