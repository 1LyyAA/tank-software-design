package ru.mipt.bit.platformer.Commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.Tank;
import ru.mipt.bit.platformer.Directions;

public class CommandTest {
    private Level level;
    private Tank tank;

    @BeforeEach
    public void setUp() {
        level = new Level();
        tank = new Tank(level, new GridPoint2(5, 5));
        level.addObject(tank);
    }

    @Test
    public void testMoveCommand() {
        MoveCommand moveCommand = new MoveCommand(tank, Directions.RIGHT);
        
        GridPoint2 initialPos = new GridPoint2(tank.getCoordinates());
        moveCommand.execute();
        
        // После выполнения команды танк должен начать движение
        assertNotEquals(1f, tank.getMovementProggress());
    }

    @Test
    public void testShootCommand() {
        int initialObjectsCount = level.getObjects().size();
        
        ShootCommand shootCommand = new ShootCommand(tank);
        shootCommand.execute();
        
        // После выстрела должен появиться новый объект (пуля)
        assertEquals(initialObjectsCount + 1, level.getObjects().size());
    }

    @Test
    public void testMoveCommandChangesRotation() {
        MoveCommand moveUp = new MoveCommand(tank, Directions.UP);
        moveUp.execute();
        
        assertEquals(90f, tank.getTankRotation());
        
        MoveCommand moveRight = new MoveCommand(tank, Directions.RIGHT);
        // Завершаем предыдущее движение
        tank.setMovementProggress(1f);
        tank.update(0.1f);
        
        moveRight.execute();
        assertEquals(0f, tank.getTankRotation());
    }
}
