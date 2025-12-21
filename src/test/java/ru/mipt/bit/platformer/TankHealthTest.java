package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.GridPoint2;

public class TankHealthTest {
    private Level level;
    private Tank tank;

    @BeforeEach
    public void setUp() {
        level = new Level();
        tank = new Tank(level, new GridPoint2(5, 5));
        level.addObject(tank);
    }

    @Test
    public void testInitialHealth() {
        assertEquals(100, tank.getHitPoints());
        assertTrue(tank.isAlive());
    }

    @Test
    public void testTakeDamage() {
        tank.takeDamage();
        assertEquals(75, tank.getHitPoints());
        assertTrue(tank.isAlive());
    }

    @Test
    public void testMultipleDamage() {
        tank.takeDamage();
        tank.takeDamage();
        tank.takeDamage();
        assertEquals(25, tank.getHitPoints());
        assertTrue(tank.isAlive());
    }

    @Test
    public void testTankDies() {
        tank.takeDamage();
        tank.takeDamage();
        tank.takeDamage();
        tank.takeDamage();
        
        assertEquals(0, tank.getHitPoints());
        assertFalse(tank.isAlive());
    }

    @Test
    public void testDeadTankRemoved() {
        int initialSize = level.getObjects().size();
        
        // Убиваем танк
        tank.takeDamage();
        tank.takeDamage();
        tank.takeDamage();
        tank.takeDamage();
        
        level.removeObjectsMarkedForRemoval();
        
        assertEquals(initialSize - 1, level.getObjects().size());
        assertFalse(level.getObjects().contains(tank));
    }
}
