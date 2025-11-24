package ru.mipt.bit.platformer.Graphics;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Interpolation;

import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.util.TileMovement;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelGraphics {
    Level level;
    private MapRenderer levelRenderer;
    private ArrayList<Graphics> graphics;
    private GraphicsFactory graphicsFactory;
    private TileMovement tileMovement;

    public LevelGraphics(Level level, Batch batch) {
        this.level = level;

        this.levelRenderer = createSingleLayerMapRenderer(level.getMap(), batch);

        this.tileMovement = new TileMovement(level.getGroundLayer(), Interpolation.smooth);

        this.graphicsFactory = new GraphicsFactory(level.getGroundLayer(), tileMovement);

        this.graphics = new ArrayList<>();
        for (int i = 0; i < level.getObjects().size(); i++) {
            this.graphics.add(graphicsFactory.createGraphicsFor(level.getObjects().get(i)));
        }
    }

    public void addObjectGraphics(GameObject object) {
        this.graphics.add(graphicsFactory.createGraphicsFor(object));
    }

    public void renderObjects(Batch batch) {
        for (Graphics graphic : graphics) {
            graphic.render(batch);
        }
    }

    public void renderMap() {
        levelRenderer.render();
    }

    public void dispose() {
        for (Graphics graphic : graphics) {
            graphic.dispose();
        }
    }
}