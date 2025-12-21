package ru.mipt.bit.platformer.Graphics;

import ru.mipt.bit.platformer.Bullet;
import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Tree;
import ru.mipt.bit.platformer.util.TileMovement;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class GraphicsFactory {
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;

    public GraphicsFactory(TiledMapTileLayer groundLayer, TileMovement tileMovement) {
        this.groundLayer = groundLayer;
        this.tileMovement = tileMovement;
    }
    
    public Graphics createGraphicsFor(GameObject gameObject) {
        if (gameObject instanceof Tank) {
            return new HealthBarDecorator(new TankGraphics((Tank) gameObject, tileMovement));
        } else if (gameObject instanceof Tree) {
            return new TreeGraphics(groundLayer, gameObject.getCoordinates(), (Tree) gameObject);
        } else if (gameObject instanceof Bullet) {
            return new BulletGraphics((Bullet) gameObject, tileMovement);
        }
        
        throw new IllegalArgumentException("Unknown game object type: " + gameObject.getClass());
    }
}