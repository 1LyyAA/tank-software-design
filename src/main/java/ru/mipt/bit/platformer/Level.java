package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;

import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Level {
    private final TiledMap map;
    private final TiledMapTileLayer groundLayer;
    private TileMovement tileMovement;
    private List<Tree> trees = new ArrayList<>();
    private Tank tank;
    private CollisionManager collisionManager;



    public Level(String path, List<Tree> trees, Tank tank, CollisionManager collisionManager) {
        map = new TmxMapLoader().load(path);
        groundLayer = (TiledMapTileLayer) map.getLayers().get(0);
        this.trees = trees;
        this.tank = tank;
        this.collisionManager = collisionManager;
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public TiledMap getMap() {
        return map;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public void dispose() {
        map.dispose();
        for (Tree tree : trees) {
            tree.dispose();
        }
        tank.dispose();
    }

    public Tank getTank() {
        return tank;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }
}