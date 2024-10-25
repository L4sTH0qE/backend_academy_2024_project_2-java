package backend.academy.model.generator;

import backend.academy.model.Maze;

/// Интерфейс, который описывает способ генерации лабиринтов по заданным высоте и ширине.
/// Реализующие его классы должны предоставлять алгоритм генерации лабиринтов.
public interface Generator {
    Maze generate(int height, int width);
}
