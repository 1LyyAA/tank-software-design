package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Level {
    private final TiledMap map;
    private final TiledMapTileLayer groundLayer;

    public Level(String path) {
        map = new TmxMapLoader().load(path);
        groundLayer = (TiledMapTileLayer) map.getLayers().get(0);
    }

    public TiledMap getMap() {
        return map;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public void dispose() {
        map.dispose();
    }
}