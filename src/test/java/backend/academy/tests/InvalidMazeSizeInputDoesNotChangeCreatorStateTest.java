package backend.academy.tests;

import backend.academy.controller.AppController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что ввод невалидного размера лабиринта
/// приводит к повторному вводу, без изменения состояния.
public class InvalidMazeSizeInputDoesNotChangeCreatorStateTest {
    int upperBound = 30;
    int firstInput = 10;
    int secondInput = 30;
    int negativeInput = -1;
    int zeroInput = 0;
    int bigInput = 50;

    @Test
    void testCorrectUserInput() {
        assertThat(AppController.checkSize(firstInput, upperBound)).isEqualTo(true);
        assertThat(AppController.checkSize(secondInput, upperBound)).isEqualTo(true);
    }

    @Test
    void testIncorrectUserInput() {
        assertThat(AppController.checkSize(negativeInput, upperBound)).isEqualTo(false);
        assertThat(AppController.checkSize(zeroInput, upperBound)).isEqualTo(false);
        assertThat(AppController.checkSize(bigInput, upperBound)).isEqualTo(false);
    }
}
