package backend.academy.controller;

import backend.academy.model.CreatorModel;
import backend.academy.model.generator.Generator;
import backend.academy.model.generator.KruskalGenerator;
import backend.academy.model.generator.PrimGenerator;
import backend.academy.model.solver.Solver;
import backend.academy.view.AppView;
import backend.academy.view.CreatorView;
import java.util.HashMap;
import java.util.Map;

/// Класс CreatorController для взаимодействия между CreatorModel и CreatorView
/// (логикой создания лабиринтов и их отображением в консоли).
///
/// @param creatorModel Объект для хранения информации о текущем состоянии лабиринта.
/// @param creatorView  Объект для отображения текущего состояния лабиринта.
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
        System.out.println("Generating maze...");
        creatorModel.generate();
        AppView.clear();
        creatorView.printMaze(creatorModel.maze());
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
    public static void initialiseData() {
        // Заполняем мапы.
        GENERATOR_MAP.put("1", new PrimGenerator());
        GENERATOR_MAP.put("2", new KruskalGenerator());
/*        SOLVER_MAP.put("1", new);
        SOLVER_MAP.put("2", new );*/
    }
}
