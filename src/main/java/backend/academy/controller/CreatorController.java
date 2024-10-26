package backend.academy.controller;

import backend.academy.model.Coordinate;
import backend.academy.model.CreatorModel;
import backend.academy.model.generator.Generator;
import backend.academy.model.generator.KruskalGenerator;
import backend.academy.model.generator.PrimGenerator;
import backend.academy.model.solver.BFSSolver;
import backend.academy.model.solver.DFSSolver;
import backend.academy.model.solver.Solver;
import backend.academy.view.AppView;
import backend.academy.view.CreatorView;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

/// Класс CreatorController для взаимодействия между CreatorModel и CreatorView
/// (логикой создания лабиринтов и их отображением в консоли).
///
/// @param creatorModel Объект для хранения информации о текущем состоянии лабиринта.
/// @param creatorView  Объект для отображения текущего состояния лабиринта.
@Log4j2
public record CreatorController(CreatorModel creatorModel, CreatorView creatorView) {

    // Используем статическую мапу для получения алгоритма генерации по ключу-строке.
    private static final Map<String, Generator> GENERATOR_MAP = new HashMap<>();

    // Используем статическую мапу для получения алгоритма нахождения пути по ключу-строке.
    private static final Map<String, Solver> SOLVER_MAP = new HashMap<>();

    /// Основной метод по созданию лабиринта.
    public void start() {
        initialiseData();
        getGenerator();
        AppView.clear();
        creatorModel.generate();
        creatorView.printMaze(creatorModel.maze()); // Выводим сгенерированный лабиринт.

        // Выводим опции и ждем выбора пользователя.
        while (true) {
            String choice = creatorView.getOptionFromUser(AppController.SCANNER);
            switch (choice) {
                case "1":
                    solveMaze();
                    break;
                case "q", "Q":
                    clearData();
                    return;
                default:
                    AppView.printInvalidCommand();
            }
        }
    }

    /// Метод для получения алгоритма для генерации лабиринта.
    @SuppressWarnings("RegexpSinglelineJava")
    public void getGenerator() {
        AppView.clear();
        while (true) {
            String choice = creatorView.getGeneratorFromUser(GENERATOR_MAP, AppController.SCANNER);
            Generator generator = GENERATOR_MAP.getOrDefault(choice, null);
            if (generator == null) {
                AppView.printInvalidCommand();
            } else {
                creatorModel.generator(generator);
                return;
            }
        }
    }

    /// Метод для получения алгоритма для нахождения пути в лабиринте.
    @SuppressWarnings("RegexpSinglelineJava")
    public void getSolver() {
        AppView.clear();
        while (true) {
            String choice = creatorView.getSolverFromUser(SOLVER_MAP, AppController.SCANNER);
            Solver solver = SOLVER_MAP.getOrDefault(choice, null);
            if (solver == null) {
                AppView.printInvalidCommand();
            } else {
                creatorModel.solver(solver);
                return;
            }
        }
    }

    /// Метод для инициализации мап для выбора алгоритмов для создания лабиринтов.
    private void initialiseData() {
        // Заполняем мапы.
        GENERATOR_MAP.put("1", new PrimGenerator());
        GENERATOR_MAP.put("2", new KruskalGenerator());
        SOLVER_MAP.put("1", new BFSSolver());
        SOLVER_MAP.put("2", new DFSSolver());
    }

    /// Метод для очистки мап для выбора алгоритмов для создания лабиринтов.
    private void clearData() {
        // Чистим мапы.
        GENERATOR_MAP.clear();
        SOLVER_MAP.clear();
    }

    /// Метод для получения координаты клетки по вертикали.
    @SuppressWarnings("RegexpSinglelineJava")
    public int getYCoord() {
        while (true) {
            String choice = creatorView.getYCoordFromUser(AppController.SCANNER);
            try {
                int yCoord = Integer.parseInt(choice);
                if (yCoord > 0 && yCoord <= ((creatorModel.height() + 1) / 2)) {
                    return yCoord;
                }
                creatorView.printInvalidYCoord();
            } catch (NumberFormatException e) {
                AppView.printInvalidValue();
            }
        }
    }

    /// Метод для получения координаты клетки по горизонтали.
    @SuppressWarnings("RegexpSinglelineJava")
    public int getXCoord() {
        while (true) {
            String choice = creatorView.getXCoordFromUser(AppController.SCANNER);
            try {
                int xCoord = Integer.parseInt(choice);
                if (xCoord > 0 && xCoord <= ((creatorModel.width() + 1) / 2)) {
                    return xCoord;
                }
                creatorView.printInvalidXCoord();
            } catch (NumberFormatException e) {
                AppView.printInvalidValue();
            }
        }
    }

    /// Метод для нахождения пути в лабиринте.
    public void solveMaze() {
        // Получаем координаты стартовой и конечной клеток пути.
        creatorView.getStartCoordsFromUser();
        int startX = getXCoord() - 1;
        int startY = getYCoord() - 1;
        Coordinate start = new Coordinate(startY * 2, startX * 2);
        creatorView.getEndCoordsFromUser();
        int endX = getXCoord() - 1;
        int endY = getYCoord() - 1;
        Coordinate end = new Coordinate(endY * 2, endX * 2);
        getSolver();
        AppView.clear();
        creatorModel.solve(start, end);
        creatorView.printMaze(creatorModel.maze(), creatorModel.path()); // Выводим лабиринт с получившимся путем.
    }
}
