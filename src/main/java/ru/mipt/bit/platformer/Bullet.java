package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Bullet implements GameObject {
    
    private GridPoint2 Coordinates;
    private GridPoint2 DestinationCoordinates;
    private final Directions direction;
    private float BulletMovementProgress = 0f;
    private final float BulletSpeed = 0.15f; 

    public Bullet(GridPoint2 tankCoordinates, float rotation) {
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
    }

    @Override
    public void update(float deltaTime) {
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
