package ru.mipt.bit.platformer.objects;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.graphics.g2d.Batch;


public class Tree implements GameObject , Collidable {
    
    private Texture greenTreeTexture;
    private TextureRegion treeObstacleGraphics;
    private GridPoint2 treeObstacleCoordinates;
    private Rectangle treeObstacleRectangle;


    public static Tree makeTreeAtTile(String internalPath, TiledMapTileLayer groundLayer, GridPoint2 tileCoordinates) {
        return new Tree(internalPath, groundLayer, tileCoordinates);
    }

    public Tree(String internalPath, TiledMapTileLayer groundLayer,  GridPoint2 tileCoordinates) {
        greenTreeTexture = new Texture(internalPath);
        treeObstacleGraphics = new TextureRegion(greenTreeTexture);
        treeObstacleCoordinates = tileCoordinates;
        treeObstacleRectangle = createBoundingRectangle(treeObstacleGraphics);

        moveRectangleAtTileCenter(groundLayer, treeObstacleRectangle, treeObstacleCoordinates);
    }

    public Rectangle getRectangle() {
        return treeObstacleRectangle;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return treeObstacleCoordinates;
    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, treeObstacleGraphics, treeObstacleRectangle, 0f);
    }

    @Override
    public void dispose() {
        greenTreeTexture.dispose();
    }

}
