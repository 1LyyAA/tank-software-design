package ru.mipt.bit.platformer;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.ArrayList;
import static java.nio.file.Files.newBufferedReader;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

public class LevelBuilder {

    private static GridPoint2 levelSizeInTiles = new GridPoint2(10, 8);

    public static Level createLevel(String tmxFilePath, String levelPath) {

        //Read from file
        String[] lvl;
        try (BufferedReader reader = newBufferedReader(Path.of(levelPath))) {
            lvl = reader.lines().toArray(String[]::new);
        } catch (Exception e) {
            System.out.println(Path.of(levelPath).toAbsolutePath());
            throw new RuntimeException("Failed to read level file", e);
        }

        Level level = new Level(tmxFilePath, new ArrayList<>(), null, new CollisionManager(new ArrayList<>()));

        for (int y = 0; y < levelSizeInTiles.y; y++) {
            for (int x = 0; x < levelSizeInTiles.x; x++) {
                char symbol = lvl[y].charAt(x);
                if (symbol == Objects.TREE.symbol.charAt(0)) {
                    level.getTrees().add(Tree.makeTreeAtTile("images/greenTree.png", level.getGroundLayer(), new GridPoint2(x, y)));
                    level.getCollisionManager().addObstacle(level.getTrees().get(level.getTrees().size() - 1));

                } else if (symbol == Objects.TANK.symbol.charAt(0)) {
                    level.setTank(Tank.makeTankAtTile(level.getCollisionManager(), level.getTileMovement()));
                    level.getTank().setTankCoordinates(new GridPoint2(x, y));
                }
            }
        }
        
        return level;
    }

    public static GridPoint2 getLevelSizeInTiles() {
        return levelSizeInTiles;
    }
}
