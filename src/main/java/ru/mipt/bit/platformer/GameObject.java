package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public interface GameObject {
    GridPoint2 getCoordinates();
    GridPoint2 getDestinationCoordinates();
    void update(float delta);
}
