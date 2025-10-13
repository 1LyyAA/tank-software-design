package ru.mipt.bit.platformer.objects;

public interface GameObject {
    void render(com.badlogic.gdx.graphics.g2d.Batch batch);

    void dispose();

}
