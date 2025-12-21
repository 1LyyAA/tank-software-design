package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.GridPoint2;

public class DirectionsTest {

    @Test
    public void testUpDirection() {
        assertEquals(0, Directions.UP.dx);
        assertEquals(1, Directions.UP.dy);
        assertEquals(90f, Directions.UP.rotation);
    }

    @Test
    public void testDownDirection() {
        assertEquals(0, Directions.DOWN.dx);
        assertEquals(-1, Directions.DOWN.dy);
        assertEquals(-90f, Directions.DOWN.rotation);
    }

    @Test
    public void testLeftDirection() {
        assertEquals(-1, Directions.LEFT.dx);
        assertEquals(0, Directions.LEFT.dy);
        assertEquals(180f, Directions.LEFT.rotation);
    }

    @Test
    public void testRightDirection() {
        assertEquals(1, Directions.RIGHT.dx);
        assertEquals(0, Directions.RIGHT.dy);
        assertEquals(0f, Directions.RIGHT.rotation);
    }

    @Test
    public void testToPoint() {
        GridPoint2 point = Directions.UP.toPoint();
        assertEquals(0, point.x);
        assertEquals(1, point.y);
    }

    @Test
    public void testFromRotation() {
        assertEquals(Directions.UP, Directions.fromRotation(90f));
        assertEquals(Directions.DOWN, Directions.fromRotation(-90f));
        assertEquals(Directions.LEFT, Directions.fromRotation(180f));
        assertEquals(Directions.RIGHT, Directions.fromRotation(0f));
    }

    @Test
    public void testRandomDirection() {
        Directions randomDir = Directions.random();
        assertNotNull(randomDir);
        assertTrue(randomDir == Directions.UP || 
                   randomDir == Directions.DOWN || 
                   randomDir == Directions.LEFT || 
                   randomDir == Directions.RIGHT);
    }
}
