package ru.mipt.bit.platformer.Observers;


import ru.mipt.bit.platformer.Bullet;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Graphics.LevelGraphics;

public class TankGraphicsObserver implements Observer<Tank> {
    LevelGraphics levelGraphics;

    public TankGraphicsObserver(LevelGraphics levelGraphics) {
        this.levelGraphics = levelGraphics;
    }
    
    @Override
    public void onCreate(Tank tank) {
        levelGraphics.addObjectGraphics(tank);
    }

    @Override
    public void onRemove(Tank tank) {
        levelGraphics.removeFor(tank);
    }
}
