package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.CollisionManager;
import ru.mipt.bit.platformer.Directions;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

//import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Tank implements GameObject, Collidable, Movable {
    TileMovement tileMovement;
    private Texture blueTankTexture;
    private TextureRegion TankGraphics;
    private Rectangle TankRectangle;
    

    private GridPoint2 TankCoordinates;
    private GridPoint2 TankDestinationCoordinates;
    private float TankSpeed = 0.4f;
    private float TankRotation;
    private float TankMovementProggress = 1f;

    CollisionManager collisionManager;

    // Texture decodes an image file and loads it into GPU memory, it represents a native resource

    public static Tank makeTankAtTile(CollisionManager collisionManager, TileMovement tileMovement) {
        return new Tank(collisionManager, tileMovement);
    }

    public Tank(CollisionManager collisionManager, TileMovement tileMovement) {
        this.collisionManager = collisionManager;
        this.tileMovement = tileMovement;

        blueTankTexture = new Texture("images/tank_blue.png");
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        TankGraphics = new TextureRegion(blueTankTexture);
        TankRectangle = createBoundingRectangle(TankGraphics);

        TankDestinationCoordinates = new GridPoint2(1, 1);
        TankCoordinates = new GridPoint2(TankDestinationCoordinates);
        TankRotation = 0f;

        
    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, TankGraphics, TankRectangle, TankRotation);
    }
    

    public Rectangle getRectangle(){
        return TankRectangle;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return TankCoordinates;
    }

    @Override
    public void dispose() {
        blueTankTexture.dispose();
    }

    // public void move (float dx, float dy ) {
    //     TankDestinationCoordinates.x += dx;
    //     TankDestinationCoordinates.y += dy;
    // }

    public void setRotation(float angle) {
        this.TankRotation = angle;
    }

    public float getMovementProggress() {
        return TankMovementProggress;
    }

    public void setMovementProggress(float x) {
        TankMovementProggress = x;
    }

    @Override
    public void update(float deltaTime) {
        // обновляем прогресс движения от 0 до 1
        TankMovementProggress = continueProgress(TankMovementProggress, deltaTime, TankSpeed);

        // плавное перемещение прямоугольника между тайлами
        this.tileMovement.moveRectangleBetweenTileCenters(TankRectangle, TankCoordinates, TankDestinationCoordinates, TankMovementProggress);

        // если достигли цели, фиксируем координаты
        if (!isMoving()) {
            TankCoordinates.set(TankDestinationCoordinates);
        }
    }

    @Override
    public boolean isMoving() {
        return !isEqual(TankMovementProggress, 1f);
    }
    
    @Override
    public void tryMove(Directions direction) {
        if (isMoving() || direction == null) {
            return;
        }

        GridPoint2 destinationCoordinates = new GridPoint2(
                TankCoordinates.x + direction.dx,
                TankCoordinates.y + direction.dy);

        if (collisionManager.isCellBlocked(destinationCoordinates)) {
            return;
        }

        TankDestinationCoordinates.set(destinationCoordinates);
        setRotation(direction.rotation);
        // move(direction.dx, direction.dy);
        setMovementProggress(0f);
    }

    public void setTankCoordinates(GridPoint2 tankCoordinates) {
        TankCoordinates = tankCoordinates;
    }
}

