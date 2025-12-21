
package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.CollisionManager;
import ru.mipt.bit.platformer.Directions;
import ru.mipt.bit.platformer.util.TileMovement;









public class TankTest {
    @Test
    public void testTankMovement() {
        // Создаем Level-заглушку для корректной работы Tank и CollisionManager
        Level dummyLevel = new Level("", new GameObject[]{});
        CollisionManager collisionManager = new CollisionManager(dummyLevel);
        // Tank принимает (Level, GridPoint2)
        Tank tank = new Tank(dummyLevel, new GridPoint2(1, 1));

        // Initial position
        assertEquals(new GridPoint2(1, 1), tank.getCoordinates());

        // Move right
        tank.tryMove(Directions.RIGHT);
        tank.setMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f);
        assertEquals(new GridPoint2(2, 1), tank.getCoordinates());

        // Move down
        tank.tryMove(Directions.DOWN);
        tank.setMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f);
        assertEquals(new GridPoint2(2, 0), tank.getCoordinates());

        // Move left
        tank.tryMove(Directions.LEFT);
        tank.setMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f);
        assertEquals(new GridPoint2(1, 0), tank.getCoordinates());

        // Move up
        tank.tryMove(Directions.UP);
        tank.setMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f);
        assertEquals(new GridPoint2(1, 1), tank.getCoordinates());

        System.out.println("All tank movement tests passed.");
    }
}
