package ru.mipt.bit.platformer;

import java.util.List;

/**
 * Класс-контейнер для данных уровня.
 * Возвращается из LevelBuilder и содержит все необходимые игровые объекты.
 */
public class LevelData {
    private final Level level;
    private final Tank playerTank;
    private final List<Tank> enemyTanks;

    public LevelData(Level level, Tank playerTank, List<Tank> enemyTanks) {
        this.level = level;
        this.playerTank = playerTank;
        this.enemyTanks = enemyTanks;
    }

    public Level getLevel() {
        return level;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }
}
