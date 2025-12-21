package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Level {
    private final TiledMap map;
    private final TiledMapTileLayer groundLayer;

    private List<GameObject> objects = new ArrayList<>();

    private CollisionManager collisionManager;
    
    public Level(String path, GameObject... initialObjects) {
        map = new TmxMapLoader().load(path);
        groundLayer = (TiledMapTileLayer) map.getLayers().get(0);

        this.objects = new ArrayList<>();
        this.objects.addAll(Arrays.asList(initialObjects));
        collisionManager = new CollisionManager(this);
    }

    public TiledMap getMap() {
        return map;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

   public void addObject(GameObject object) {
        objects.add(object);
    }

    public void addObjects(List<GameObject> objs) {
        this.objects.addAll(objs);
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }
}