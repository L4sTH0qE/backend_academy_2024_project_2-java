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
import lombok.extern.log4j.Log4j2;
import java.util.HashMap;
import java.util.Map;

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
        try {
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
                        return;
                    default:
                        AppView.printInvalidCommand();
                }
            }
            // Если возникла ошибка во время работы программы.
        } catch (Exception ex) {
            log.error("An unexpected error occurred while the program was running!");
            log.error("Details: {}", ex.getMessage());
            AppController.exit();
        }
    }

    /// Метод для получения алгоритма для генерации лабиринта.
    @SuppressWarnings("RegexpSinglelineJava")
    private void getGenerator() {
        AppView.clear();
        while (true) {
            String choice = creatorView.getGeneratorFromUser(GENERATOR_MAP);
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
    private void getSolver() {
        AppView.clear();
        while (true) {
            String choice = creatorView.getSolverFromUser(SOLVER_MAP);
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
    public void initialiseData() {
        // Заполняем мапы.
        GENERATOR_MAP.put("1", new PrimGenerator());
        GENERATOR_MAP.put("2", new KruskalGenerator());
        SOLVER_MAP.put("1", new BFSSolver());
        SOLVER_MAP.put("2", new DFSSolver());
    }

    /// Метод для получения координаты клетки по вертикали.
    @SuppressWarnings("RegexpSinglelineJava")
    private int getYCoord() {
        while (true) {
            String choice = creatorView.getYCoordFromUser(AppController.SCANNER);
            try {
                int yCoord = Integer.parseInt(choice);
                if (yCoord >= 0 && yCoord < ((creatorModel.height() + 1) / 2)) {
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
    private int getXCoord() {
        while (true) {
            String choice = creatorView.getXCoordFromUser(AppController.SCANNER);
            try {
                int xCoord = Integer.parseInt(choice);
                if (xCoord >= 0 && xCoord < ((creatorModel.width() + 1) / 2)) {
                    return xCoord;
                }
                creatorView.printInvalidXCoord();
            } catch (NumberFormatException e) {
                AppView.printInvalidValue();
            }
        }
    }

    /// Метод для инициализации координат стартовой и конечной точки для нахождения пути в лабиринте.
    private Coordinate[] initialiseCoords() {
        // Получаем координаты стартовой и конечной клеток пути.
        creatorView.getStartCoordsFromUser();
        int startX = getXCoord();
        int startY = getYCoord();
        Coordinate start = new Coordinate(startY, startX);
        creatorView.getEndCoordsFromUser();
        int endX = getXCoord();
        int endY = getYCoord();
        Coordinate end = new Coordinate(endY, endX);

        return new Coordinate[] {start, end};
    }

    /// Метод для нахождения пути в лабиринте.
    private void solveMaze() {
        Coordinate[] coords = initialiseCoords();
        getSolver();
        AppView.clear();
        creatorModel.solve(coords[0], coords[1]);
        creatorView.printMaze(creatorModel.maze(), creatorModel.path()); // Выводим лабиринт с получившимся путем.
    }
}
