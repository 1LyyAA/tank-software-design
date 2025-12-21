package ru.mipt.bit.platformer.Observers;

import ru.mipt.bit.platformer.GameObject;

public interface Observer<T extends GameObject> {
    void onCreate(T object);
    void onRemove(T object);

}
