package ru.mipt.bit.platformer.LevelLoaders;

import static ru.mipt.bit.platformer.Objects.ENEMY;
import static ru.mipt.bit.platformer.Objects.TANK;
import static ru.mipt.bit.platformer.Objects.TREE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Tree;

public class RandomLevelGenerator implements LevelGenerator {
    private GridPoint2 levelSizeInTiles = new GridPoint2(10, 8);
    private String tmxFilePath;
    
    public RandomLevelGenerator(String tmxFilePath) {
        this.tmxFilePath = tmxFilePath;
    }

    @Override
    public LevelData generate() {
        String[] Lvl = makeRandomLevelLayout();
        return generateLvl(Lvl, tmxFilePath);
    }

    private LevelData generateLvl(String[] Lvl, String tmxFilePath) {
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
                } else if (symbol == ENEMY.symbol) {
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

    private String[] makeRandomLevelLayout() {
        String[] lvl = new String[levelSizeInTiles.y];
        StringBuilder sb = new StringBuilder();
        

        for (int i = 0; i < levelSizeInTiles.y; i++) {
            for (int j = 0; j < levelSizeInTiles.x; j++) {
                char result = new char[]{'T', '_'}[new Random().nextInt(2)];
                sb.append(result);
            }
            lvl[i] = sb.toString();
            sb.setLength(0);
        }

        int x = new Random().nextInt(levelSizeInTiles.x);
        int y = new Random().nextInt(levelSizeInTiles.y);

        String row = lvl[y];
        lvl[y] = row.substring(0, x) + "X" + row.substring(x + 1);
        
        return lvl;
    }
    
}
