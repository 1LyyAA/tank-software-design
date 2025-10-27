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

import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

public class LevelBuilder {

    private static GridPoint2 levelSizeInTiles = new GridPoint2(10, 8);

    public static Level createLevel(String tmxFilePath, String levelPath) {

        //Read from file
        String[] invertedLvl;
        try (BufferedReader reader = newBufferedReader(Path.of(levelPath))) {
            invertedLvl = reader.lines().toArray(String[]::new);
        } catch (Exception e) {
            System.out.println(Path.of(levelPath).toAbsolutePath());
            throw new RuntimeException("Failed to read level file", e);
        }


        //invert lvl
        String[] Lvl = new String[invertedLvl.length];
        for (int i = 0; i < invertedLvl.length; i++) {
            Lvl[i] = invertedLvl[invertedLvl.length - 1 - i];
        }

        Level level = new Level(tmxFilePath, new ArrayList<>(), null, new CollisionManager(new ArrayList<>()));

        for (int y = 0; y < levelSizeInTiles.y; y++) {
            for (int x = 0; x < levelSizeInTiles.x; x++) {
                char symbol = Lvl[y].charAt(x);
                if (symbol == Objects.TREE.symbol.charAt(0)) {
                    level.getTrees().add(Tree.makeTreeAtTile("images/greenTree.png", level.getGroundLayer(), new GridPoint2(x, y)));
                    level.getCollisionManager().addObstacle(level.getTrees().get(level.getTrees().size() - 1));
                } else if (symbol == Objects.TANK.symbol.charAt(0)) {
                    level.setTank(Tank.makeTankAtTile(level.getCollisionManager(), level.getTileMovement()));
                    level.getTank().setTankCoordinates(new GridPoint2(x, y));
                    level.getTank().setTankDestinationCoordinates(new GridPoint2(x, y));
                }
            }
        }
        
        return level;
    }

    public static Level createRandomLevel(String tmxFilePath) {
        // TO DO: implement random level generation

        
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < levelSizeInTiles.y; i++) {
            for (int j = 0; j < levelSizeInTiles.x; j++) {
                char result = new char[]{'T', '_'}[new Random().nextInt(2)];
                sb.append(result);
            }
            sb.append('\n');
        }

        int x = new Random().nextInt(levelSizeInTiles.x);;
        int y = new Random().nextInt(levelSizeInTiles.y);

        sb.replace(y * (levelSizeInTiles.x + 1) + x, y * (levelSizeInTiles.x + 1) + x + 1, "X");



        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/images/RandomLevel.txt"))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write random level file", e);
        }
        
        return createLevel(tmxFilePath, "src/main/resources/images/RandomLevel.txt");
    }




    public static GridPoint2 getLevelSizeInTiles() {
        return levelSizeInTiles;
    }
}
