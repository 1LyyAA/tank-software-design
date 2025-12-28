package ru.mipt.bit.platformer.LevelLoaders;

import java.util.List;

import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.Tank;

public class LevelData {
    private final Level level;
    private final Tank playerTank;
    private final List<Tank> enemyTanks;
    private final String tmxFilePath;

    public LevelData(Level level, Tank playerTank, List<Tank> enemyTanks, String tmxFilePath) {
        this.level = level;
        this.playerTank = playerTank;
        this.enemyTanks = enemyTanks;
        this.tmxFilePath = tmxFilePath;
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

    public String getTmxFilePath() {
        return tmxFilePath;
    }
}
