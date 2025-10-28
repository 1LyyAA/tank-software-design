package ru.mipt.bit.platformer.objects;

public enum Objects {
    TREE('T'),
    TANK('X'),
    BLANK('_');

    public final char symbol;
    Objects(char symbol) {
        this.symbol = symbol;
    }
}
