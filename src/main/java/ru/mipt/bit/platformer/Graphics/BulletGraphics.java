package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.Bullet;
import ru.mipt.bit.platformer.util.TileMovement;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class BulletGraphics implements Graphics {
    private final Bullet bullet;

    private Texture bulletTexture = new Texture("images/bullet.png");
    private TextureRegion bulletGraphics = new TextureRegion(bulletTexture);
    private Rectangle bulletRectangle = createBoundingRectangle(bulletGraphics);
    private TileMovement tileMovement;

    public BulletGraphics(Bullet bullet, TileMovement tileMovement) {
        this.bullet = bullet;
        this.tileMovement = tileMovement;
    }

    @Override
    public void render(Batch batch) {
        // if (!bullet.isActive()) {
        //     return;
        // }
        
        this.tileMovement.moveRectangleBetweenTileCenters(bulletRectangle, bullet.getCoordinates(), 
                                                        bullet.getDestinationCoordinates(), 
                                                        bullet.getMovementProgress());
        drawTextureRegionUnscaled(batch, bulletGraphics, bulletRectangle, bullet.getBulletRotation());
    }

    @Override
    public void dispose() {
        bulletTexture.dispose();
    }

    public Bullet getBullet() {
        return bullet;
    }

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public TextureRegion getBulletGraphics() {
        return bulletGraphics;
    }

    public Rectangle getBulletRectangle() {
        return bulletRectangle;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }
}
