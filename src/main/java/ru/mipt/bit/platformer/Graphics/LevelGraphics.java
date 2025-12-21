package ru.mipt.bit.platformer.Graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;

import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.util.TileMovement;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelGraphics {
    Level level;
    private final TiledMap map;
    private final TiledMapTileLayer groundLayer;
    private MapRenderer levelRenderer;
    private Map<GameObject, Graphics> graphics;
    private GraphicsFactory graphicsFactory;
    private TileMovement tileMovement;

    public LevelGraphics(Level level, String tmxPath, Batch batch) {
        this.level = level;
        
        this.map = new TmxMapLoader().load(tmxPath);
        this.groundLayer = (TiledMapTileLayer) map.getLayers().get(0);

        this.levelRenderer = createSingleLayerMapRenderer(map, batch);

        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        this.graphicsFactory = new GraphicsFactory(groundLayer, tileMovement);

        this.graphics = new HashMap<>();

        for (GameObject object : level.getObjects()) {
            this.graphics.put(object, graphicsFactory.createGraphicsFor(object));
        }
    }

    public void addObjectGraphics(GameObject object) {
        this.graphics.put(object, graphicsFactory.createGraphicsFor(object));
    }

    public void removeFor(GameObject object) {
        Graphics graphic = graphics.get(object);
        if (graphic != null) {
            graphic.dispose();
            graphics.remove(object);
        }
    }
    

    public void renderObjects(Batch batch) {
        // while (graphics.size() < level.getObjects().size()) {
        //     GameObject newObject = level.getObjects().get(graphics.size());
        //     graphics.put(newObject, graphicsFactory.createGraphicsFor(newObject));
        // }
        
        for (Graphics graphic : graphics.values()) {
            graphic.render(batch);
        }
    }

    public void renderMap() {
        levelRenderer.render();
    }

    public void dispose() {
        for (Graphics graphic : graphics.values()) {
            graphic.dispose();
        }
    }
}