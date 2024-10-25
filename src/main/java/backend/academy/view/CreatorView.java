package backend.academy.view;

import backend.academy.controller.AppController;
import backend.academy.model.Coordinate;
import backend.academy.model.Maze;
import backend.academy.model.generator.Generator;
import backend.academy.model.renderer.DefaultRenderer;
import backend.academy.model.renderer.Renderer;
import backend.academy.model.solver.Solver;
import lombok.Getter;
import java.util.List;
import java.util.Map;

/// Класс CreatorView для отображения процесса создания лабиринтов в консоли.
@Getter public class CreatorView {

    // Поле для получения лабиринта в строковом представлении.
    private final Renderer renderer = new DefaultRenderer();

    public CreatorView() {
    }

    /// Метод для вывода сообщения обо всех значениях в мапе с их ключами.
    @SuppressWarnings("RegexpSinglelineJava")
    public <T> void printMap(Map<String, T> classMap) {
        for (Map.Entry<String, T> entry : classMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getClass().getSimpleName());
        }
    }

    /// Метод для вывода лабиринта без пути в консоль.
    public void printMaze(Maze maze) {
        System.out.println(renderer.render(maze));
    }

    /// Метод для вывода лабиринта с путем в консоль.
    public void printMaze(Maze maze, List<Coordinate> path) {
        System.out.println(renderer.render(maze, path));
    }

    /// Метод для вывода сообщения об ожидании выбора пользователем алгоритма для генерации лабиринта.
    @SuppressWarnings("RegexpSinglelineJava")
    public String getGeneratorFromUser(Map<String, Generator> generatorMap) {
        System.out.println("Choose maze generator's algorithm:");
        printMap(generatorMap);
        AppView.printInput();
        String choice = AppController.SCANNER.nextLine();
        System.out.println();
        return choice;
    }

    /// Метод для вывода сообщения об ожидании выбора пользователем алгоритма для нахождения пути в лабиринте.
    @SuppressWarnings("RegexpSinglelineJava")
    public String getSolverFromUser(Map<String, Solver> solverMap) {
        System.out.println("Choose maze pathfinder's algorithm:");
        printMap(solverMap);
        AppView.printInput();
        String choice = AppController.SCANNER.nextLine();
        System.out.println();
        return choice;
    }
}
