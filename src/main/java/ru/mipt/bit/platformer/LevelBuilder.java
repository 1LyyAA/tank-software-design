package ru.mipt.bit.platformer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import static java.nio.file.Files.newBufferedReader;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.objects.*;
import static ru.mipt.bit.platformer.objects.Objects.*;


public class LevelBuilder {

    private static GridPoint2 levelSizeInTiles = new GridPoint2(10, 8);

    public static Level createLevel(String tmxFilePath, String levelPath) {
        String[] invertedLvl = getInvertedLvl(levelPath);
        String[] Lvl = invertLvl(invertedLvl);
        Level level = generateLvl(tmxFilePath, Lvl);
        
        return level;
    }

    public static Level createRandomLevel(String tmxFilePath) {
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

    private static Level generateLvl(String tmxFilePath, String[] Lvl) {
        Level level = new Level(tmxFilePath, new ArrayList<>(), null, new CollisionManager(new ArrayList<>()));

        for (int y = 0; y < levelSizeInTiles.y; y++) {
            for (int x = 0; x < levelSizeInTiles.x; x++) {
                char symbol = Lvl[y].charAt(x);
                if (symbol == TREE.symbol) {
                    addTrees(level, y, x);
                } else if (symbol == TANK.symbol) {
                    addTank(level, y, x);
                }
            }
        }
        return level;
    }

    private static void addTank(Level level, int y, int x) {
        level.setTank(Tank.makeTankAtTile(level.getCollisionManager(), level.getTileMovement()));
        level.getTank().setTankCoordinates(new GridPoint2(x, y));
        level.getTank().setTankDestinationCoordinates(new GridPoint2(x, y));
    }

    private static void addTrees(Level level, int y, int x) {
        level.getTrees().add(Tree.makeTreeAtTile("images/greenTree.png", level.getGroundLayer(), new GridPoint2(x, y)));
        level.getCollisionManager().addObstacle(level.getTrees().get(level.getTrees().size() - 1));
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
        try (BufferedReader reader = newBufferedReader(Path.of(levelPath))) {
            invertedLvl = reader.lines().toArray(String[]::new);
        } catch (Exception e) {
            System.out.println(Path.of(levelPath).toAbsolutePath());
            throw new RuntimeException("Failed to read level file", e);
        }
        return invertedLvl;
    }
}
