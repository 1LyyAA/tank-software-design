package ru.mipt.bit.platformer.Observers;

import java.util.logging.Level;

import ru.mipt.bit.platformer.Bullet;
import ru.mipt.bit.platformer.Graphics.LevelGraphics;

public class BulletGraphicsObserver implements Observer<Bullet> {
    LevelGraphics levelGraphics;

    public BulletGraphicsObserver(LevelGraphics levelGraphics) {
        this.levelGraphics = levelGraphics;
    }
    
    @Override
    public void onCreate(Bullet bullet) {
        levelGraphics.addObjectGraphics(bullet);
    }

    @Override
    public void onRemove(Bullet bullet) {
        levelGraphics.removeFor(bullet);
    }
}