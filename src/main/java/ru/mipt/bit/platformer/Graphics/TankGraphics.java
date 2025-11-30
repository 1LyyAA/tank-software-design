package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;


public class TankGraphics implements Graphics {
    private Texture blueTankTexture = new Texture("images/tank_blue.png");
    private TextureRegion TankGraphics = new TextureRegion(blueTankTexture);
    private Rectangle TankRectangle = createBoundingRectangle(TankGraphics);
    TileMovement tileMovement;
    Tank tank;

    public TankGraphics(Tank tank, TileMovement tileMovement) {
        this.tank = tank;
        this.tileMovement = tileMovement;
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
    }

    
}