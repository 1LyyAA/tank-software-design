package ru.mipt.bit.platformer.LevelLoaders;

import java.io.BufferedReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.Files.newBufferedReader;
import static ru.mipt.bit.platformer.Objects.*;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.Objects;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Tree;
import java.util.List;

public class FileLevelGenerator implements LevelGenerator {
    
    private static GridPoint2 levelSizeInTiles = new GridPoint2(10, 8);
    private String tmxFilePath;
    private String levelPath;

    public FileLevelGenerator(String tmxFilePath, String levelPath) {
        this.tmxFilePath = tmxFilePath;
        this.levelPath = levelPath;
    }

    @Override
    public LevelData generate() {
        String[] invertedLvl = getInvertedLvl(levelPath);
        String[] Lvl = invertLvl(invertedLvl);
        return generateLvl(tmxFilePath, Lvl);
    }

    public static GridPoint2 getLevelSizeInTiles() {
        return levelSizeInTiles;
    }

    private static LevelData generateLvl(String tmxFilePath, String[] Lvl) {
        Level level = new Level();
        List<GameObject> objects = new ArrayList<>();
        Tank playerTank = null;
        List<Tank> enemyTanks = new ArrayList<>();
        
        for (int y = 0; y < levelSizeInTiles.y; y++) {
            for (int x = 0; x < levelSizeInTiles.x; x++) {
                char symbol = Lvl[y].charAt(x);
                if (symbol == TREE.symbol) {
                    objects.add(Tree.makeTreeAt(new GridPoint2(x, y)));
                } else if (symbol == TANK.symbol) {
                    // X - игрок
                    playerTank = Tank.makeTankAtTile(level, new GridPoint2(x, y));
                    objects.add(playerTank);
                } else if (symbol == Objects.ENEMY.symbol) {
                    // E - враг
                    Tank enemy = Tank.makeEnemyTank(level, new GridPoint2(x, y));
                    enemyTanks.add(enemy);
                    objects.add(enemy);
                }
            }
        }

        level.addObjects(objects);
        return new LevelData(level, playerTank, enemyTanks, tmxFilePath);
    }

    private static String[] invertLvl(String[] invertedLvl) {
        String[] Lvl = new String[invertedLvl.length];
        for (int i = 0; i < invertedLvl.length; i++) {
            Lvl[i] = invertedLvl[invertedLvl.length - 1 - i];
        }
        return Lvl;
    }

    private static String[] getInvertedLvl(String levelPath) {
        String[] invertedLvl;
        try (BufferedReader reader = newBufferedReader(Paths.get(levelPath))) {
            invertedLvl = reader.lines().toArray(String[]::new);
        } catch (Exception e) {
            System.out.println(Paths.get(levelPath).toAbsolutePath());
            throw new RuntimeException("Failed to read level file", e);
        }
        return invertedLvl;
    }
}
