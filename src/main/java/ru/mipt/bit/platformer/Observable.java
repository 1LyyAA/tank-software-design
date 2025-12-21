package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.Observers.Observer;

public interface Observable {
    public <T extends GameObject> void addListener(Class<T> type, Observer<? super T> observer);

}
