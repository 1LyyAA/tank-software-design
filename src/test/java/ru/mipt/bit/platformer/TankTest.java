package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.math.GridPoint2;

public class TankTest {
    
    @Test
    public void testTankMovement() {
        Level level = new Level();
        Tank tank = new Tank(level, new GridPoint2(1, 1));

        // Initial position
        assertEquals(new GridPoint2(1, 1), tank.getCoordinates());

        // Move right
        tank.tryMove(Directions.RIGHT);
        tank.setMovementProggress(1f);
        tank.update(0.1f);
        assertEquals(new GridPoint2(2, 1), tank.getCoordinates());

        // Move down
        tank.tryMove(Directions.DOWN);
        tank.setMovementProggress(1f);
        tank.update(0.1f);
        assertEquals(new GridPoint2(2, 0), tank.getCoordinates());

        // Move left
        tank.tryMove(Directions.LEFT);
        tank.setMovementProggress(1f);
        tank.update(0.1f);
        assertEquals(new GridPoint2(1, 0), tank.getCoordinates());

        // Move up
        tank.tryMove(Directions.UP);
        tank.setMovementProggress(1f); 
        tank.update(0.1f);
        assertEquals(new GridPoint2(1, 1), tank.getCoordinates());
    }
}
