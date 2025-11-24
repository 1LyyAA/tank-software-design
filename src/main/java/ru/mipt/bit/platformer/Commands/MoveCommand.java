package ru.mipt.bit.platformer.Commands;

import ru.mipt.bit.platformer.Directions;
import ru.mipt.bit.platformer.Tank;

public class MoveCommand implements Command {
    Tank tank;
    Directions direction;

    public MoveCommand(Tank tank, Directions direction) {
        this.tank = tank;
        this.direction = direction;
    }

    @Override
    public void execute() {
        tank.tryMove(direction);
    }
}