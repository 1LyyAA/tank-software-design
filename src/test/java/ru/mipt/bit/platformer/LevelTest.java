package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.GridPoint2;
import java.util.List;

public class LevelTest {
    private Level level;
    private Tank tank1;
    private Tank tank2;

    @BeforeEach
    public void setUp() {
        level = new Level();
        tank1 = new Tank(level, new GridPoint2(0, 0));
        tank2 = new Tank(level, new GridPoint2(5, 5));
    }

    @Test
    public void testAddObject() {
        level.addObject(tank1);
        assertEquals(1, level.getObjects().size());
        assertTrue(level.getObjects().contains(tank1));
    }

    @Test
    public void testAddMultipleObjects() {
        level.addObject(tank1);
        level.addObject(tank2);
        assertEquals(2, level.getObjects().size());
    }

    @Test
    public void testRemoveObject() {
        level.addObject(tank1);
        level.addObject(tank2);
        
        level.removeObject(tank1);
        level.removeObjectsMarkedForRemoval();
        
        assertEquals(1, level.getObjects().size());
        assertFalse(level.getObjects().contains(tank1));
        assertTrue(level.getObjects().contains(tank2));
    }

    @Test
    public void testCollisionManagerExists() {
        assertNotNull(level.getCollisionManager());
    }
}
