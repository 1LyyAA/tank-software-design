package ru.mipt.bit.platformer.objects;

import ru.mipt.bit.platformer.Directions;

public interface Movable {
    void tryMove(Directions direction);

    boolean isMoving();

    void update(float delta);
}
