package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Bullet implements GameObject {
    
    private final GridPoint2 coordinates;
    private final GridPoint2 destinationCoordinates;
    private final Directions direction;
    private float bulletMovementProgress = 1f;
    private final float speed = 300f;

    public Bullet(GridPoint2 coordinates, float rotation) {
        
        
        Directions direction = Directions.fromRotation(rotation);

        this.coordinates = new GridPoint2(coordinates.x + direction.dx, coordinates.y + direction.dy);

        this.destinationCoordinates = new GridPoint2(coordinates);

        this.destinationCoordinates.x += direction.dx * speed;
        this.destinationCoordinates.y += direction.dy * speed;

        this.direction = direction;
    }

    public void update(float deltaTime) {
        float distance = speed * deltaTime;
        coordinates.x += direction.dx * distance;
        coordinates.y += direction.dy * distance;
        destinationCoordinates.x += direction.dx * distance;
        destinationCoordinates.y += direction.dy * distance;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Directions getDirection() {
        return direction;
    }

    public float getBulletRotation() {
        return direction.rotation;
    }

    public float getMovementProgress() {
        return bulletMovementProgress;
    }
}
