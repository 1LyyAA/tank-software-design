package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.Observers.*;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Bullet implements GameObject{
    
    private Level level;
    private GridPoint2 Coordinates;
    private GridPoint2 DestinationCoordinates;
    private final Directions direction;
    private float BulletMovementProgress = 0f;
    private final float BulletSpeed = 0.15f; 
    private  boolean isAlive = true;

    public Bullet(GridPoint2 tankCoordinates, float rotation, Level level) {
        this.level = level;
        Directions direction = Directions.fromRotation(rotation);
        this.direction = direction;
        
        Coordinates = new GridPoint2(
            tankCoordinates.x + direction.dx,
            tankCoordinates.y + direction.dy
        );
        
        DestinationCoordinates = new GridPoint2(
            Coordinates.x + direction.dx,
            Coordinates.y + direction.dy
        );
        
        for (Observer<?> observer : level.getObserversFor(Bullet.class)) {
            BulletGraphicsObserver obs = (BulletGraphicsObserver) observer;
            obs.onCreate(this);
        }
    }

    @Override
    public void update(float deltaTime) {
        updateCoords(deltaTime);
        if (!isAlive) {
            return;
        }
        this.isAlive = !level.getCollisionManager().checkBulletCollisions(DestinationCoordinates);
        if (!isAlive) {
            for (Observer<?> observer : level.getObserversFor(Bullet.class)) {
                BulletGraphicsObserver obs = (BulletGraphicsObserver) observer;
                obs.onRemove(this);
            }
        }
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    private void updateCoords(float deltaTime) {
        BulletMovementProgress = continueProgress(BulletMovementProgress, deltaTime, BulletSpeed);
        if (isEqual(BulletMovementProgress, 1f)) {
            Coordinates.set(DestinationCoordinates);
            DestinationCoordinates.add(direction.dx, direction.dy);
            BulletMovementProgress = 0f;
        }
    }

    public GridPoint2 getDestinationCoordinates() {
        return this.DestinationCoordinates;
    }

    public GridPoint2 getCoordinates() {
        return this.Coordinates;
    }

    public Directions getDirection() {
        return direction;
    }

    public float getBulletRotation() {
        return direction.rotation;
    }

    public float getMovementProgress() {
        return this.BulletMovementProgress;
    }
}
