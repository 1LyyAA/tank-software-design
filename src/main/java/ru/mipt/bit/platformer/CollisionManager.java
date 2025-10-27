package ru.mipt.bit.platformer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Collidable;


public class CollisionManager {

    private final List<Collidable> obstacles;

    public CollisionManager(List<? extends Collidable> obstacles) {
        this.obstacles = new ArrayList<>(obstacles);
    }

    public boolean isCellBlocked(GridPoint2 destinationCoordinates) {
        for (Collidable object : obstacles) {
            if (object.getCoordinates().equals(destinationCoordinates)) {
                return true;
            }
        }
        return false;
    }

    public List<Collidable> getObstacles() {
        return obstacles;
    }

    public void addObstacle(Collidable obstacle) {
        obstacles.add(obstacle);
    }
}



