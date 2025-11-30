package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;

import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

import org.w3c.dom.Text;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;


public class TankGraphics implements Graphics {
    private Texture blueTankTexture = new Texture("images/tank_blue.png");
    private TextureRegion TankGraphics = new TextureRegion(blueTankTexture);
    private Rectangle TankRectangle = createBoundingRectangle(TankGraphics);
    private ShapeRenderer shapeRenderer;
    TileMovement tileMovement;
    Tank tank;

    public TankGraphics(Tank tank, TileMovement tileMovement) {
        this.tank = tank;
        this.tileMovement = tileMovement;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render (Batch batch) {
        this.tileMovement.moveRectangleBetweenTileCenters(TankRectangle, tank.getCoordinates(), 
                                                        tank.getDestinationCoordinates(), 
                                                        tank.getMovementProggress());
        drawTextureRegionUnscaled(batch, TankGraphics, TankRectangle, tank.getTankRotation());
        
        
    }
    
    @Override
    public void dispose() {
        blueTankTexture.dispose();
        shapeRenderer.dispose();
    }

    public Texture getBlueTankTexture() {
        return blueTankTexture;
    }

    public TextureRegion getTankGraphics() {
        return TankGraphics;
    }

    public Rectangle getTankRectangle() {
        return TankRectangle;
    }

    public Tank getTank() {
        return tank;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }



    

}