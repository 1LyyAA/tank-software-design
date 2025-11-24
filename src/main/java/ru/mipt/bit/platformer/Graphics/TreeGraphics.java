package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.Tree;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TreeGraphics implements Graphics {
    private Texture greenTreeTexture;
    private TextureRegion treeObstacleGraphics;
    private Rectangle treeObstacleRectangle;
    private Tree tree;

    public TreeGraphics(TiledMapTileLayer groundLayer,  GridPoint2 tileCoordinates, Tree tree) {
        greenTreeTexture = new Texture("images/greenTree.png");
        treeObstacleGraphics = new TextureRegion(greenTreeTexture);
        this.tree = tree;
        treeObstacleRectangle = createBoundingRectangle(treeObstacleGraphics);

        moveRectangleAtTileCenter(groundLayer, treeObstacleRectangle, tree.getCoordinates());
    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, treeObstacleGraphics, treeObstacleRectangle, 0f);
    }

    @Override
    public void dispose() {
        greenTreeTexture.dispose();
    }

    public Tree getTree() {
        return tree;
    }

}
