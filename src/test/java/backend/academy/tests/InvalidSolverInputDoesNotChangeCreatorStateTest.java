package backend.academy.tests;

import backend.academy.controller.CreatorController;
import backend.academy.model.CreatorModel;
import backend.academy.view.CreatorView;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что ввод невалидного номера алгоритма нахождения пути
/// приводит к повторному вводу, без изменения состояния.
public class InvalidSolverInputDoesNotChangeCreatorStateTest {
    static int height = 10;
    static int width = 20;
    static CreatorController creatorController =
        new CreatorController(new CreatorModel(height, width), new CreatorView());

    @BeforeAll static void initialiseMap() {
        creatorController.initialiseData();
    }

    @AfterAll static void clearMap() {
        creatorController.clearData();
    }

    String firstSolver = "1";
    String secondSolver = "2";
    String firstSolverName = "BFSSolver";
    String secondSolverName = "DFSSolver";
    String wrongSolver = "3";
    String emptySolver = "";

    @Test
    void testCorrectUserInput() {
        creatorController.getSolver(firstSolver);
        assertThat(creatorController.creatorModel().solver().getClass().getSimpleName()).isEqualTo(firstSolverName);
        creatorController.getSolver(secondSolver);
        assertThat(creatorController.creatorModel().solver().getClass().getSimpleName()).isEqualTo(secondSolverName);
    }

    @Test
    void testIncorrectUserInput() {
        creatorController.getSolver(wrongSolver);
        assertThat(creatorController.creatorModel().solver()).isNull();
        creatorController.getSolver(emptySolver);
        assertThat(creatorController.creatorModel().solver()).isNull();
    }
}
