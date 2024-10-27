package backend.academy.tests;

import backend.academy.controller.CreatorController;
import backend.academy.model.CreatorModel;
import backend.academy.view.CreatorView;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что ввод невалидного номера алгоритма генерации лабиринта
/// приводит к повторному вводу, без изменения состояния.
public class InvalidGeneratorInputDoesNotChangeCreatorStateTest {
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

    String firstGenerator = "1";
    String secondGenerator = "2";
    String firstGeneratorName = "PrimGenerator";
    String secondGeneratorName = "KruskalGenerator";
    String wrongGenerator = "3";
    String emptyGenerator = "";

    @Test
    void testCorrectUserInput() {
        creatorController.getGenerator(firstGenerator);
        assertThat(creatorController.creatorModel().generator().getClass().getSimpleName()).isEqualTo(firstGeneratorName);
        creatorController.getGenerator(secondGenerator);
        assertThat(creatorController.creatorModel().generator().getClass().getSimpleName()).isEqualTo(secondGeneratorName);
    }

    @Test
    void testIncorrectUserInput() {
        creatorController.getGenerator(wrongGenerator);
        assertThat(creatorController.creatorModel().generator()).isNull();
        creatorController.getGenerator(emptyGenerator);
        assertThat(creatorController.creatorModel().generator()).isNull();
    }
}
