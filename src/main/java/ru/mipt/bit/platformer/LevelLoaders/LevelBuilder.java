package ru.mipt.bit.platformer.LevelLoaders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import static java.nio.file.Files.newBufferedReader;
import static ru.mipt.bit.platformer.Objects.*;
import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.Objects;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Tree;

import java.util.List;

public class LevelBuilder {

    private static GridPoint2 levelSizeInTiles = new GridPoint2(10, 8);

    public static LevelData createLevel(String tmxFilePath, String levelPath) {
        String[] invertedLvl = getInvertedLvl(levelPath);
        String[] Lvl = invertLvl(invertedLvl);
        return generateLvl(tmxFilePath, Lvl);
    }

    public static LevelData createRandomLevel(String tmxFilePath) {
        String Lvl = makeRandomLevelLayout();
        exportLvl(Lvl);
        
        return createLevel(tmxFilePath, "src/main/resources/images/RandomLevel.txt");
    }

    public static GridPoint2 getLevelSizeInTiles() {
        return levelSizeInTiles;
    }

    private static void exportLvl(String Lvl, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(Lvl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write level file", e);
        }
    }

    private static void exportLvl(String Lvl) {
        exportLvl(Lvl,"src/main/resources/images/RandomLevel.txt");
    }

    private static String makeRandomLevelLayout() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < levelSizeInTiles.y; i++) {
            for (int j = 0; j < levelSizeInTiles.x; j++) {
                char result = new char[]{'T', '_'}[new Random().nextInt(2)];
                sb.append(result);
            }
            sb.append('\n');
        }

        int x = new Random().nextInt(levelSizeInTiles.x);
        int y = new Random().nextInt(levelSizeInTiles.y);

        sb.replace(y * (levelSizeInTiles.x + 1) + x, y * (levelSizeInTiles.x + 1) + x + 1, "X");
        return sb.toString();
    }

    private static LevelData generateLvl(String tmxFilePath, String[] Lvl) {
        Level level = new Level(tmxFilePath);
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
        return new LevelData(level, playerTank, enemyTanks);
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
