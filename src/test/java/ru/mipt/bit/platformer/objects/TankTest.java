
package ru.mipt.bit.platformer.objects;

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
        CollisionManager collisionManager = new CollisionManager(List.of());
        Tank tank = new Tank(collisionManager);
        TileMovement tileMovement = new TileMovement(null, Interpolation.smooth);

        // Initial position
        assertEquals(new GridPoint2(1, 1), tank.getTankCoordinates());

        // Move right
        tank.tryMove(Directions.RIGHT);
        tank.setTankMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f, tileMovement);
        assertEquals(new GridPoint2(2, 1), tank.getTankCoordinates());

        // Move down
        tank.tryMove(Directions.DOWN);
        tank.setTankMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f, tileMovement);
        assertEquals(new GridPoint2(2, 0), tank.getTankCoordinates());

        // Move left
        tank.tryMove(Directions.LEFT);
        tank.setTankMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f, tileMovement);
        assertEquals(new GridPoint2(1, 0), tank.getTankCoordinates());

        // Move up
        tank.tryMove(Directions.UP);
        tank.setTankMovementProggress(1f); // Simulate instant movement for testing
        tank.update(0.1f, tileMovement);
        assertEquals(new GridPoint2(1, 1), tank.getTankCoordinates());

        //System.out.println("All tank movement tests passed.");
    }
}
