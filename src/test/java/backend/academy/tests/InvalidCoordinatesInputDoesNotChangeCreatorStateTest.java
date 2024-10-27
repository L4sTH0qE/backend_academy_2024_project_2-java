package backend.academy.tests;

import backend.academy.controller.CreatorController;
import backend.academy.model.CreatorModel;
import backend.academy.view.CreatorView;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/// Проверка, что ввод невалидных координат стартовой и конечной точек для нахождения пути
/// приводит к повторному вводу, без изменения состояния.
public class InvalidCoordinatesInputDoesNotChangeCreatorStateTest {
    int height = 10;
    int width = 20;
    CreatorController creatorController =
        new CreatorController(new CreatorModel(height, width), new CreatorView());
    int xyInput = 5;
    int yMaxInput = 10;
    int xOnlyInput = 15;
    int xMaxInput = 20;
    int negativeInput = -1;
    int zeroInput = 0;
    int largeInput = 50;

    @Test
    void testCorrectUserInput() {
        assertThat(creatorController.checkXCoord(xyInput)).isEqualTo(true);
        assertThat(creatorController.checkYCoord(xyInput)).isEqualTo(true);

        assertThat(creatorController.checkXCoord(yMaxInput)).isEqualTo(true);
        assertThat(creatorController.checkYCoord(yMaxInput)).isEqualTo(true);

        assertThat(creatorController.checkXCoord(xOnlyInput)).isEqualTo(true);

        assertThat(creatorController.checkXCoord(xMaxInput)).isEqualTo(true);
    }

    @Test
    void testIncorrectUserInput() {
        assertThat(creatorController.checkYCoord(xOnlyInput)).isEqualTo(false);

        assertThat(creatorController.checkYCoord(xMaxInput)).isEqualTo(false);

        assertThat(creatorController.checkXCoord(negativeInput)).isEqualTo(false);
        assertThat(creatorController.checkYCoord(negativeInput)).isEqualTo(false);

        assertThat(creatorController.checkXCoord(zeroInput)).isEqualTo(false);
        assertThat(creatorController.checkYCoord(zeroInput)).isEqualTo(false);

        assertThat(creatorController.checkXCoord(largeInput)).isEqualTo(false);
        assertThat(creatorController.checkYCoord(largeInput)).isEqualTo(false);
    }
}
