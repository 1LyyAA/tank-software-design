package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.Directions;

public class InputHandler {
    public static Directions getInput() {
        for (Directions dir : Directions.values()) {
            if (dir.isPressed()) {
                return dir;
            }
        }
        return null;
    }

    
}
