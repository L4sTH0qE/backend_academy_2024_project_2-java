package backend.academy.model;

import backend.academy.model.generator.Generator;
import backend.academy.model.solver.Solver;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/// Класс для описания логики создателя лабиринтов и текущего состояния лабиринта.
public class CreatorModel {

    // Поле, хранящее алгоритм для генерации лабиринта.
    @Setter private Generator generator;

    // Поле, хранящее алгоритм для нахождения пути в лабиринте.
    @Setter private Solver solver;

    // Поле, хранящее сгенерированный лабиринт.
    @Getter @Setter private Maze maze;

    // Поле, хранящее путь в лабиринте.
    @Getter @Setter private List<Coordinate> path;

    // Поле, хранящее информацию о количестве клеток в высоте лабиринта.
    @Getter private final int height;

    // Поле, хранящее информацию о количестве клеток в ширине лабиринта.
    @Getter private final int width;

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
