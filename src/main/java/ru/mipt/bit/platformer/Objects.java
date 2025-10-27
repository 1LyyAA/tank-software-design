package ru.mipt.bit.platformer;

public enum Objects {
    TREE("T"),
    TANK("X"),
    BLANK("_");

    public final String symbol;
    Objects(String symbol) {
        this.symbol = symbol;
    }
}
