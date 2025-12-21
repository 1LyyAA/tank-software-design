package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public enum Directions {
    UP(0, 1, 90f),
    LEFT(-1, 0, 180f),
    DOWN(0, -1, -90f),
    RIGHT(1, 0, 0f);

    public final int dx;
    public final int dy;
    public final float rotation;

    Directions(int dx, int dy, float rotation) {
        this.dx = dx;
        this.dy = dy;
        this.rotation = rotation;
    }

    public GridPoint2 toPoint() {
        return new GridPoint2(dx, dy);
    }

    public static Directions random() {
        Directions[] directions = Directions.values();
        int index = (int) (Math.random() * directions.length);
        return directions[index];
    }

    public static Directions fromRotation(float rotation) {
        for (Directions direction : Directions.values()) {
            if (direction.rotation == rotation) {
                return direction;
            }
        }
        return null; // or throw an exception if preferred
    }
    
}
