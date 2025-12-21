package ru.mipt.bit.platformer.Commands;

import ru.mipt.bit.platformer.Tank;

public class ShootCommand implements Command {
    Tank tank;

    public ShootCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.shoot();
    }
}
